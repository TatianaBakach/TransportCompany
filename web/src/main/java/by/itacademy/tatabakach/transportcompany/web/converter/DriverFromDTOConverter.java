package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDriver;
import by.itacademy.tatabakach.transportcompany.service.IDriverService;
import by.itacademy.tatabakach.transportcompany.web.dto.DriverDTO;

@Component
public class DriverFromDTOConverter implements Function<DriverDTO, IDriver> {
	
	@Autowired
	private IDriverService driverService;

	@Override
	public IDriver apply(final DriverDTO dto) {
		final IDriver entity = driverService.createEntity();
		entity.setId(dto.getId());
		entity.setFirstName(dto.getFirstName());
		entity.setMiddleName(dto.getMiddleName());
		entity.setLastName(dto.getLastName());
		entity.setPassportData(dto.getPassportData());
		entity.setPhone(dto.getPhone());
		return entity;
	}
	
}
