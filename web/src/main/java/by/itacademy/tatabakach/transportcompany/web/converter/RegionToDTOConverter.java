package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICountry;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRegion;
import by.itacademy.tatabakach.transportcompany.web.dto.RegionDTO;

@Component
public class RegionToDTOConverter implements Function<IRegion, RegionDTO> {

	@Override
	public RegionDTO apply(final IRegion entity) {
		final RegionDTO dto = new RegionDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());

		ICountry country = entity.getCountry();
		if (country != null) {
			dto.setCountryName(country.getName());
			dto.setCountryId(country.getId());
		}

		return dto;
	}

}
