package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICar;
import by.itacademy.tatabakach.transportcompany.service.ICarService;
import by.itacademy.tatabakach.transportcompany.web.dto.CarDTO;


@Component
public class CarFromDTOConverter implements Function<CarDTO, ICar> {

	@Autowired
	private ICarService carService;

	@Override
	public ICar apply(final CarDTO dto) {
		final ICar entity = carService.createEntity();
		entity.setId(dto.getId());
		entity.setNumber(dto.getNumber());
		entity.setModel(dto.getModel());
		return entity;
	}
}
