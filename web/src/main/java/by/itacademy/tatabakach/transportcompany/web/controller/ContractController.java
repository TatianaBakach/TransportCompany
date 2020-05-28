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

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IContract;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.ContractFilter;
import by.itacademy.tatabakach.transportcompany.service.ICompanyService;
import by.itacademy.tatabakach.transportcompany.service.IContractService;
import by.itacademy.tatabakach.transportcompany.web.converter.ContractFromDTOConverter;
import by.itacademy.tatabakach.transportcompany.web.converter.ContractToDTOConverter;
import by.itacademy.tatabakach.transportcompany.web.dto.ContractDTO;
import by.itacademy.tatabakach.transportcompany.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/contract")
public class ContractController extends AbstractController {
	
	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private IContractService contractService;

	@Autowired
	private ContractToDTOConverter toDtoConverter;
	
	@Autowired
	private ContractFromDTOConverter fromDtoConverter;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req, 
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final ContractFilter filter = new ContractFilter();
		filter.setFetchCompany(true);
		filter.setFetchOurCompany(true);
		prepareFilter(gridState, filter);

		final List<IContract> entities = contractService.find(filter);
		List<ContractDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(contractService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("contract.list", models);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IContract newEntity = contractService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));
		loadCommonFormModels(hashMap);

		return new ModelAndView("contract.edit", hashMap);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final ContractDTO formModel, final BindingResult result) {
		
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			loadCommonFormModels(hashMap);

			return new ModelAndView("contract.edit", hashMap);
		} else {
			final IContract entity = fromDtoConverter.apply(formModel);
			contractService.save(entity);
			return "redirect:/contract";
		}
	}
	
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		contractService.delete(id);
		return "redirect:/contract";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IContract dbModel = contractService.getFullInfo(id);
		final ContractDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		
		loadCommonFormModels(hashMap);

		return new ModelAndView("contract.edit", hashMap);
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final ContractDTO dto = toDtoConverter.apply(contractService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		
		loadCommonFormModels(hashMap);

		return new ModelAndView("contract.edit", hashMap);
	}
	
	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		
		final List<ICompany> companies = companyService.getAll();
		Map<Integer, String> companiesMap = new HashMap<Integer, String>();
		for (ICompany iCompany : companies) {
			companiesMap.put(iCompany.getId(), iCompany.getName());
		}
		hashMap.put("companiesChoices", companiesMap);

	}

}