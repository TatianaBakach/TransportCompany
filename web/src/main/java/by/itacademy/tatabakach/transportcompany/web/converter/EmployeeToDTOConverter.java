package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.web.dto.EmployeeDTO;

@Component
public class EmployeeToDTOConverter implements Function<IEmployee, EmployeeDTO> {
	
	@Override
	public EmployeeDTO apply(final IEmployee entity) {
		final EmployeeDTO dto = new EmployeeDTO();
		dto.setId(entity.getId());
		dto.setFirstName(entity.getFirstName());
		dto.setMiddleName(entity.getMiddleName());
		dto.setLastName(entity.getLastName());
		dto.setDepartment(entity.getDepartment());
		dto.setPosition(entity.getPosition());
		dto.setEMail(entity.getEMail());
		dto.setPhone(entity.getPhone());
		dto.setLogin(entity.getLogin());
		dto.setPassword(entity.getPassword());
		dto.setSalary(entity.getSalary());

		return dto;
	}

}
