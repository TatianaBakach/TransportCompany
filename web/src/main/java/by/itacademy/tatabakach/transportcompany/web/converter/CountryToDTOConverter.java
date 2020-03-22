package by.itacademy.tatabakach.transportcompany.web.converter;

import org.springframework.stereotype.Component;

import java.util.function.Function;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICountry;
import by.itacademy.tatabakach.transportcompany.web.dto.CountryDTO;

@Component
public class CountryToDTOConverter implements Function<ICountry, CountryDTO> {
	
	@Override
	public CountryDTO apply(final ICountry entity) {
		final CountryDTO dto = new CountryDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}

}
