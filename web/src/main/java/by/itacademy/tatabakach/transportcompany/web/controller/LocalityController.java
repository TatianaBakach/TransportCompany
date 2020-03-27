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

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ILocality;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.LocalityFilter;
import by.itacademy.tatabakach.transportcompany.service.ILocalityService;
import by.itacademy.tatabakach.transportcompany.web.converter.LocalityFromDTOConverter;
import by.itacademy.tatabakach.transportcompany.web.converter.LocalityToDTOConverter;
import by.itacademy.tatabakach.transportcompany.web.dto.LocalityDTO;
import by.itacademy.tatabakach.transportcompany.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/locality")
public class LocalityController extends AbstractController {
	
	@Autowired
	private ILocalityService localityService;

	@Autowired
	private LocalityToDTOConverter toDtoConverter;
	
	@Autowired
	private LocalityFromDTOConverter fromDtoConverter;
	

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req, 
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		
		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final LocalityFilter filter = new LocalityFilter();
		filter.setFetchRegion(true);
		prepareFilter(gridState, filter);

		final List<ILocality> entities = localityService.find(filter);
		List<LocalityDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(localityService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("locality.list", models);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final ILocality newEntity = localityService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));

		return new ModelAndView("locality.edit", hashMap);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final LocalityDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "locality.edit";
		} else {
			final ILocality entity = fromDtoConverter.apply(formModel);
			localityService.save(entity);
			return "redirect:/locality";
		}
	}
	
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		localityService.delete(id);
		return "redirect:/locality";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ILocality dbModel = localityService.getFullInfo(id);
		final LocalityDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("locality.edit", hashMap);
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final LocalityDTO dto = toDtoConverter.apply(localityService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("locality.edit", hashMap);
	}

}