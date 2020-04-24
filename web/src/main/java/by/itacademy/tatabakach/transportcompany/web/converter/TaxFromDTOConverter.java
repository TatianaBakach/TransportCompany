package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITax;
import by.itacademy.tatabakach.transportcompany.service.ITaxService;
import by.itacademy.tatabakach.transportcompany.web.dto.TaxDTO;

@Component
public class TaxFromDTOConverter implements Function<TaxDTO, ITax> {

	@Autowired
	private ITaxService taxService;

	@Override
	public ITax apply(final TaxDTO dto) {
		final ITax entity = taxService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setRate(dto.getRate());
		return entity;
	}
}
