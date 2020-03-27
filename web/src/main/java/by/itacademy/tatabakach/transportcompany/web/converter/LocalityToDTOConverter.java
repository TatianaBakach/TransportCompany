package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ILocality;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRegion;
import by.itacademy.tatabakach.transportcompany.web.dto.LocalityDTO;

@Component
public class LocalityToDTOConverter implements Function<ILocality, LocalityDTO> {

	@Override
	public LocalityDTO apply(final ILocality entity) {
		final LocalityDTO dto = new LocalityDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());

		IRegion region = entity.getRegion();
		if (region != null) {
			dto.setRegionName(region.getName());
			dto.setRegionId(region.getId());
		}

		return dto;
	}

}
