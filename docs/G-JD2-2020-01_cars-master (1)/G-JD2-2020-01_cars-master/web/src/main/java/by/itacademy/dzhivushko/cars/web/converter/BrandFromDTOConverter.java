package by.itacademy.dzhivushko.cars.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.itacademy.dzhivushko.cars.dao.api.entity.table.IBrand;
import by.itacademy.dzhivushko.cars.service.IBrandService;
import by.itacademy.dzhivushko.cars.web.dto.BrandDTO;

@Component
public class BrandFromDTOConverter implements Function<BrandDTO, IBrand> {

	@Autowired
	private IBrandService brandService;

	@Override
	public IBrand apply(final BrandDTO dto) {
		final IBrand entity = brandService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		return entity;
	}
}
