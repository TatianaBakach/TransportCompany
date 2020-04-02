package by.itacademy.dzhivushko.cars.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import by.itacademy.dzhivushko.cars.dao.api.entity.table.ICar;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IModel;
import by.itacademy.dzhivushko.cars.dao.api.filter.CarFilter;
import by.itacademy.dzhivushko.cars.service.ICarService;
import by.itacademy.dzhivushko.cars.service.IModelService;
import by.itacademy.dzhivushko.cars.web.converter.CarFromDTOConverter;
import by.itacademy.dzhivushko.cars.web.converter.CarToDTOConverter;
import by.itacademy.dzhivushko.cars.web.dto.CarDTO;
import by.itacademy.dzhivushko.cars.web.dto.grid.GridStateDTO;
import by.itacademy.dzhivushko.cars.web.dto.search.CarSearchDTO;

@Controller
@RequestMapping(value = "/car")
public class CarController extends AbstractController {
	private static final String SEARCH_FORM_MODEL = "searchFormModel";
	private static final String SEARCH_DTO = CarController.class.getSimpleName() + "_SEACH_DTO";

	@Autowired
	private ICarService carService;
	@Autowired
	private IModelService modelService;
	@Autowired
	private CarFromDTOConverter fromDtoConverter;

	@Autowired
	private CarToDTOConverter toDtoConverter;

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView index(final HttpServletRequest req, @ModelAttribute(SEARCH_FORM_MODEL) CarSearchDTO searchDto,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {
		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		if (req.getMethod().equalsIgnoreCase("get")) {
			// do not use empty payload which comes in case of GET
			searchDto = getSearchDTO(req);
		} else {
			req.getSession().setAttribute(SEARCH_DTO, searchDto);
		}

		final CarFilter filter = new CarFilter();
		if (searchDto.getVin() != null) {
			filter.setVin(searchDto.getVin());
		}

		if ((searchDto.getFreeOnly() != null) && searchDto.getFreeOnly()) {
			filter.setSold(Boolean.FALSE);
		} else {
			filter.setSold(null);
		}

		prepareFilter(gridState, filter);

		final List<ICar> entities = carService.find(filter);
		List<CarDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(carService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		models.put(SEARCH_FORM_MODEL, searchDto);

		final ICar newestCar = carService.getNewestCar();
		models.put("newestCarId", newestCar == null ? null : newestCar.getId());

		return new ModelAndView("car.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();

		ICar newCar = carService.createEntity();
		final CarDTO dto = toDtoConverter.apply(newCar);

		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("car.edit", hashMap);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final CarDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("car.edit", hashMap);
		} else {
			final ICar entity = fromDtoConverter.apply(formModel);
			carService.save(entity);
			return "redirect:/car";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		carService.delete(id);
		return "redirect:/car";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ICar dbModel = carService.getFullInfo(id);
		final CarDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("car.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final CarDTO dto = toDtoConverter.apply(carService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("car.edit", hashMap);
	}

	@RequestMapping(value = "/lastId", method = RequestMethod.GET)
	public ResponseEntity<Integer> getNewestCar() {
		final ICar newestCar = carService.getNewestCar();
		return new ResponseEntity<Integer>(newestCar == null ? null : newestCar.getId(), HttpStatus.OK);
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {

		final Map<Integer, String> engineTypesMap = modelService.getAll().stream()
				.collect(Collectors.toMap(IModel::getId, IModel::getName));

		hashMap.put("modelsChoices", engineTypesMap);

	}

	private CarSearchDTO getSearchDTO(final HttpServletRequest req) {
		CarSearchDTO searchDTO = (CarSearchDTO) req.getSession().getAttribute(SEARCH_DTO);
		if (searchDTO == null) {
			searchDTO = new CarSearchDTO();
			req.getSession().setAttribute(SEARCH_DTO, searchDTO);
		}
		return searchDTO;
	}
}
