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

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.CompanyType;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IAddress;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.AddressFilter;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CompanyFilter;
import by.itacademy.tatabakach.transportcompany.service.IAddressService;
import by.itacademy.tatabakach.transportcompany.service.ICompanyService;
import by.itacademy.tatabakach.transportcompany.service.IEmployeeService;
import by.itacademy.tatabakach.transportcompany.web.converter.CompanyFromDTOConverter;
import by.itacademy.tatabakach.transportcompany.web.converter.CompanyToDTOConverter;
import by.itacademy.tatabakach.transportcompany.web.dto.CompanyDTO;
import by.itacademy.tatabakach.transportcompany.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/company")
public class CompanyController extends AbstractController {

	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private IAddressService addressService;

	@Autowired
	private ICompanyService companyService;

	@Autowired
	private CompanyToDTOConverter toDtoConverter;

	@Autowired
	private CompanyFromDTOConverter fromDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final CompanyFilter filter = new CompanyFilter();
		filter.setFetchLegalAddress(true);
		filter.setFetchPostAddress(true);
		filter.setFetchCreator(true);
		prepareFilter(gridState, filter);

		final List<ICompany> entities = companyService.find(filter);
		List<CompanyDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(companyService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("company.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final ICompany newEntity = companyService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));

		loadCommonFormModels(hashMap);

		return new ModelAndView("company.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final CompanyDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			loadCommonFormModels(hashMap);

			return new ModelAndView("company.edit", hashMap);
		} else {
			final ICompany entity = fromDtoConverter.apply(formModel);
			companyService.save(entity);
			return "redirect:/company";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		companyService.delete(id);
		return "redirect:/company";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ICompany dbModel = companyService.getFullInfo(id);
		final CompanyDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		loadCommonFormModels(hashMap);

		return new ModelAndView("company.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final CompanyDTO dto = toDtoConverter.apply(companyService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		loadCommonFormModels(hashMap);

		return new ModelAndView("company.edit", hashMap);
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		final AddressFilter filter = new AddressFilter();
		filter.setFetchLocality(true);
		final List<IAddress> adresses = addressService.find(filter);
		Map<Integer, String> addressesMap = new HashMap<Integer, String>();
		for (IAddress iAddress : adresses) {
			addressesMap.put(iAddress.getId(), String.format("%s %s %s", iAddress.getPostcode(), iAddress.getLocality().getName(), iAddress.getExactAddress()));
		}
		// final Map<Integer, String> brandsMap = countries.stream()
		// .collect(Collectors.toMap(ICountry::getId, ICountry::getName));
		hashMap.put("addressesChoices", addressesMap);

		final List<CompanyType> companyTypesList = Arrays.asList(CompanyType.values());
		final Map<String, String> companyTypesMap = companyTypesList.stream()
				.collect(Collectors.toMap(CompanyType::name, CompanyType::name));

		hashMap.put("companyTypesChoices", companyTypesMap);

		final List<IEmployee> employees = employeeService.getAll();
		Map<Integer, String> employeesMap = new HashMap<Integer, String>();
		for (IEmployee iEmployee : employees) {
			employeesMap.put(iEmployee.getId(), String.format("%s %s",iEmployee.getLastName(), iEmployee.getFirstName()));
		}
		hashMap.put("employeesChoices", employeesMap);

	}

}
