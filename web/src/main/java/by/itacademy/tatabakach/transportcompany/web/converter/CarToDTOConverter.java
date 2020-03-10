package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICar;
import by.itacademy.tatabakach.transportcompany.web.dto.CarDTO;

@Component
public class CarToDTOConverter implements Function<ICar, CarDTO> {
	
	@Override
	public CarDTO apply(final ICar entity) {
		final CarDTO dto = new CarDTO();
		dto.setId(entity.getId());
		dto.setModel(entity.getModel());
		dto.setNumber(entity.getNumber());
		return dto;
	}

}
