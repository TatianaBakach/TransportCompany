package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICountry;
import by.itacademy.tatabakach.transportcompany.service.ICountryService;
import by.itacademy.tatabakach.transportcompany.web.dto.CountryDTO;

@Component
public class CountryFromDTOConverter implements Function<CountryDTO, ICountry>{
	
	@Autowired
	private ICountryService countryService;

	@Override
	public ICountry apply(final CountryDTO dto) {
		final ICountry entity = countryService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		return entity;
	}

}
