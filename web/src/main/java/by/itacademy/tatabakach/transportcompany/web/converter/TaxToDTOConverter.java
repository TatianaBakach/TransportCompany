package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITax;
import by.itacademy.tatabakach.transportcompany.web.dto.TaxDTO;

@Component
public class TaxToDTOConverter implements Function<ITax, TaxDTO> {
	
	@Override
	public TaxDTO apply(final ITax entity) {
		final TaxDTO dto = new TaxDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setRate(entity.getRate());
		return dto;
	}

}
