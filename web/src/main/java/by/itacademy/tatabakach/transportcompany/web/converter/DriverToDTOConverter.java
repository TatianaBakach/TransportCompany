package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDriver;
import by.itacademy.tatabakach.transportcompany.web.dto.DriverDTO;

@Component
public class DriverToDTOConverter implements Function<IDriver, DriverDTO>{
	
	@Override
	public DriverDTO apply(final IDriver entity) {
		final DriverDTO dto = new DriverDTO();
		dto.setId(entity.getId());
		dto.setFirstName(entity.getFirstName());
		dto.setMiddleName(entity.getMiddleName());
		dto.setLastName(entity.getLastName());
		dto.setPassportData(entity.getPassportData());
		dto.setPhone(entity.getPhone());
		return dto;
	}

}
