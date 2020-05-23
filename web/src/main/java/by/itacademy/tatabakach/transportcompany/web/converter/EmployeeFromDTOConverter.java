package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.service.IEmployeeService;
import by.itacademy.tatabakach.transportcompany.web.dto.EmployeeDTO;


@Component
public class EmployeeFromDTOConverter implements Function<EmployeeDTO, IEmployee> {

	@Autowired
	private IEmployeeService  employeeService;

	@Override
	public IEmployee apply(final EmployeeDTO dto) {
		final IEmployee entity = employeeService.createEntity();
		entity.setId(dto.getId());
		entity.setFirstName(dto.getFirstName());
		entity.setMiddleName(dto.getMiddleName());
		entity.setLastName(dto.getLastName());
		entity.setDepartment(dto.getDepartment());
		entity.setPosition(dto.getPosition());
		entity.setMail(dto.getMail());
		entity.setPhone(dto.getPhone());
		entity.setRole(dto.getRole());
		entity.setPassword(dto.getPassword());
		entity.setSalary(dto.getSalary());
		return entity;
	}
}
