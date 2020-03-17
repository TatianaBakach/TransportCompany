package by.itacademy.tatabakach.transportcompany.web.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICar;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CarFilter;
import by.itacademy.tatabakach.transportcompany.service.ICarService;
import by.itacademy.tatabakach.transportcompany.web.converter.CarFromDTOConverter;
import by.itacademy.tatabakach.transportcompany.web.converter.CarToDTOConverter;
import by.itacademy.tatabakach.transportcompany.web.dto.CarDTO;
import by.itacademy.tatabakach.transportcompany.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/car")
public class CarController extends AbstractController {
	
	@Autowired
	private ICarService carService;

	@Autowired
	private CarToDTOConverter toDtoConverter;
	
	@Autowired
	private CarFromDTOConverter fromDtoConverter;
	

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req, 
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		
		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final CarFilter filter = new CarFilter();
		prepareFilter(gridState, filter);

		final List<ICar> entities = carService.find(filter);
		List<CarDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(carService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("car.list", models);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final ICar newEntity = carService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));

		return new ModelAndView("car.edit", hashMap);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final CarDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "car.edit";
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
		final ICar dbModel = carService.get(id);
		final CarDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("car.edit", hashMap);
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final CarDTO dto = toDtoConverter.apply(carService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("car.edit", hashMap);
	}

}