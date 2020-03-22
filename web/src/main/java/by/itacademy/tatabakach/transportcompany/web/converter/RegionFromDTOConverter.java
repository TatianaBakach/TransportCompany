package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRegion;
import by.itacademy.tatabakach.transportcompany.service.IRegionService;
import by.itacademy.tatabakach.transportcompany.web.dto.RegionDTO;


@Component
public class RegionFromDTOConverter implements Function<RegionDTO, IRegion> {

	@Autowired
	private IRegionService regionService;

	@Override
	public IRegion apply(final RegionDTO dto) {
		final IRegion entity = regionService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setCountry(dto.getCountry());
		return entity;
	}
}
