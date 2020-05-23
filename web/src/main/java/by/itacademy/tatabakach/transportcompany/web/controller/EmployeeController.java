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

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.Department;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.Position;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.Role;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.EmployeeFilter;
import by.itacademy.tatabakach.transportcompany.service.IEmployeeService;
import by.itacademy.tatabakach.transportcompany.web.converter.EmployeeFromDTOConverter;
import by.itacademy.tatabakach.transportcompany.web.converter.EmployeeToDTOConverter;
import by.itacademy.tatabakach.transportcompany.web.dto.EmployeeDTO;
import by.itacademy.tatabakach.transportcompany.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController extends AbstractController {
	
	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private EmployeeToDTOConverter toDtoConverter;
	
	@Autowired
	private EmployeeFromDTOConverter fromDtoConverter;
	

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req, 
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		
		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final EmployeeFilter filter = new EmployeeFilter();
		prepareFilter(gridState, filter);

		final List<IEmployee> entities = employeeService.find(filter);
		List<EmployeeDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(employeeService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("employee.list", models);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IEmployee newEntity = employeeService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));
		
		 loadCommonFormModels(hashMap);

		return new ModelAndView("employee.edit", hashMap);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final EmployeeDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
            hashMap.put("formModel", formModel);
            loadCommonFormModels(hashMap);
            return new ModelAndView("employee.edit", hashMap);
		} else {
			final IEmployee entity = fromDtoConverter.apply(formModel);
			employeeService.save(entity);
			return "redirect:/employee";
		}
	}
	
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		employeeService.delete(id);
		return "redirect:/employee";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IEmployee dbModel = employeeService.get(id);
		final EmployeeDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		
		loadCommonFormModels(hashMap);

		return new ModelAndView("employee.edit", hashMap);
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final EmployeeDTO dto = toDtoConverter.apply(employeeService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		
		loadCommonFormModels(hashMap);

		return new ModelAndView("employee.edit", hashMap);
	}
	
	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		
        final List<Department> departmentTypesList = Arrays.asList(Department.values());
        final Map<String, String> departmentTypesMap = departmentTypesList.stream()
                .collect(Collectors.toMap(Department::name, Department::name));

        hashMap.put("departmentChoices", departmentTypesMap);
        
        final List<Position> positionTypesList = Arrays.asList(Position.values());
        final Map<String, String> positionTypesMap = positionTypesList.stream()
                .collect(Collectors.toMap(Position::name, Position::name));

        hashMap.put("positionChoices", positionTypesMap);
        
        final List<Role> roleTypesList = Arrays.asList(Role.values());
        final Map<String, String> roleTypesMap = roleTypesList.stream()
                .collect(Collectors.toMap(Role::name, Role::name));

        hashMap.put("roleChoices", roleTypesMap);

    }

}