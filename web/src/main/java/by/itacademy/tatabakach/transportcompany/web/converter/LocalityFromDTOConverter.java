package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ILocality;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRegion;
import by.itacademy.tatabakach.transportcompany.service.ILocalityService;
import by.itacademy.tatabakach.transportcompany.service.IRegionService;
import by.itacademy.tatabakach.transportcompany.web.dto.LocalityDTO;

@Component
public class LocalityFromDTOConverter implements Function<LocalityDTO, ILocality> {

	@Autowired
	private ILocalityService localityService;

	@Autowired
	private IRegionService regionService;

	@Override
	public ILocality apply(final LocalityDTO dto) {
		final ILocality entity = localityService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());

		Integer regionId = dto.getRegionId();
		if (regionId != null) {
			IRegion r = regionService.createEntity();
			r.setId(regionId);
			entity.setRegion(r);
		}

		return entity;
	}
}
