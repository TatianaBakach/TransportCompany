package by.itacademy.dzhivushko.cars.web.controller;

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

import by.itacademy.dzhivushko.cars.dao.api.entity.table.IBrand;
import by.itacademy.dzhivushko.cars.dao.api.filter.BrandFilter;
import by.itacademy.dzhivushko.cars.service.IBrandService;
import by.itacademy.dzhivushko.cars.web.converter.BrandFromDTOConverter;
import by.itacademy.dzhivushko.cars.web.converter.BrandToDTOConverter;
import by.itacademy.dzhivushko.cars.web.dto.BrandDTO;
import by.itacademy.dzhivushko.cars.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/brand")
public class BrandController extends AbstractController {

	@Autowired
	private IBrandService brandService;
	@Autowired
	private BrandToDTOConverter toDtoConverter;
	@Autowired
	private BrandFromDTOConverter fromDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final BrandFilter filter = new BrandFilter();
		prepareFilter(gridState, filter);

		final List<IBrand> entities = brandService.find(filter);
		List<BrandDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(brandService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("brand.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IBrand newEntity = brandService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));

		return new ModelAndView("brand.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final BrandDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "brand.edit";
		} else {
			final IBrand entity = fromDtoConverter.apply(formModel);
			brandService.save(entity);
			return "redirect:/brand";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		brandService.delete(id);
		return "redirect:/brand";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IBrand dbModel = brandService.get(id);
		final BrandDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("brand.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final BrandDTO dto = toDtoConverter.apply(brandService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("brand.edit", hashMap);
	}
}
