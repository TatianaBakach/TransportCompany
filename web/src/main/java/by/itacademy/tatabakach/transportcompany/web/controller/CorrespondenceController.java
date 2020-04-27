package by.itacademy.tatabakach.transportcompany.web.controller;

import java.util.Arrays;
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

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.CorrespondenceType;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICorrespondence;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CorrespondenceFilter;
import by.itacademy.tatabakach.transportcompany.service.ICompanyService;
import by.itacademy.tatabakach.transportcompany.service.ICorrespondenceService;
import by.itacademy.tatabakach.transportcompany.service.IOrderService;
import by.itacademy.tatabakach.transportcompany.web.converter.CorrespondenceFromDTOConverter;
import by.itacademy.tatabakach.transportcompany.web.converter.CorrespondenceToDTOConverter;
import by.itacademy.tatabakach.transportcompany.web.dto.CorrespondenceDTO;
import by.itacademy.tatabakach.transportcompany.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/correspondence")
public class CorrespondenceController extends AbstractController {
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private ICorrespondenceService correspondenceService;

	@Autowired
	private CorrespondenceToDTOConverter toDtoConverter;
	
	@Autowired
	private CorrespondenceFromDTOConverter fromDtoConverter;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req, 
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final CorrespondenceFilter filter = new CorrespondenceFilter();
		filter.setFetchOrder(true);
		filter.setFetchCompany(true);
		prepareFilter(gridState, filter);

		final List<ICorrespondence> entities = correspondenceService.find(filter);
		List<CorrespondenceDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(correspondenceService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("correspondence.list", models);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final ICorrespondence newEntity = correspondenceService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));
		loadCommonFormModels(hashMap);

		return new ModelAndView("correspondence.edit", hashMap);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final CorrespondenceDTO formModel, final BindingResult result) {
		
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			loadCommonFormModels(hashMap);

			return new ModelAndView("correspondence.edit", hashMap);
		} else {
			final ICorrespondence entity = fromDtoConverter.apply(formModel);
			correspondenceService.save(entity);
			return "redirect:/correspondence";
		}
	}
	
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		correspondenceService.delete(id);
		return "redirect:/correspondence";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ICorrespondence dbModel = correspondenceService.getFullInfo(id);
		final CorrespondenceDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		
		loadCommonFormModels(hashMap);

		return new ModelAndView("correspondence.edit", hashMap);
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final CorrespondenceDTO dto = toDtoConverter.apply(correspondenceService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		
		loadCommonFormModels(hashMap);

		return new ModelAndView("correspondence.edit", hashMap);
	}
	
	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		
		final List<CorrespondenceType> correspondenceTypesList = Arrays.asList(CorrespondenceType.values());
		final Map<String, String> correspondenceTypesMap = correspondenceTypesList.stream()
				.collect(Collectors.toMap(CorrespondenceType::name, CorrespondenceType::name));

		hashMap.put("correspondenceTypesChoices", correspondenceTypesMap);
		
		final List<IOrder> orders = orderService.getAll();
		Map<Integer, String> ordersMap = new HashMap<Integer, String>();
		for (IOrder iOrder : orders) {
			ordersMap.put(iOrder.getId(), iOrder.getNumber());
		}
		hashMap.put("ordersChoices", ordersMap);
		
		final List<ICompany> companies = companyService.getAll();
		Map<Integer, String> companiesMap = new HashMap<Integer, String>();
		for (ICompany iCompany : companies) {
			companiesMap.put(iCompany.getId(), iCompany.getName());
		}
		hashMap.put("companiesChoices", companiesMap);

	}

}