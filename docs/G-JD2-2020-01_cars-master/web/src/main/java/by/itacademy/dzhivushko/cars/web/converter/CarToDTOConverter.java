package by.itacademy.dzhivushko.cars.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.dzhivushko.cars.dao.api.entity.table.ICar;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IModel;
import by.itacademy.dzhivushko.cars.web.dto.CarDTO;

@Component
public class CarToDTOConverter implements Function<ICar, CarDTO> {

	@Override
	public CarDTO apply(final ICar entity) {
		final CarDTO dto = new CarDTO();
		dto.setId(entity.getId());
		dto.setVin(entity.getVin());
		dto.setSold(entity.getSold());
		dto.setSoldDate(entity.getSoldDate());
		dto.setSoldTime(entity.getSoldDate());
		final IModel model = entity.getModel();
		if (entity.getModel() != null) {
			dto.setModelId(model.getId());
			dto.setModelName(model.getName());
		}
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		return dto;
	}
}
