package by.itacademy.dzhivushko.cars.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.dzhivushko.cars.dao.api.entity.table.IBrand;
import by.itacademy.dzhivushko.cars.web.dto.BrandDTO;

@Component
public class BrandToDTOConverter implements Function<IBrand, BrandDTO> {

	@Override
	public BrandDTO apply(final IBrand entity) {
		final BrandDTO dto = new BrandDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		return dto;
	}

}
