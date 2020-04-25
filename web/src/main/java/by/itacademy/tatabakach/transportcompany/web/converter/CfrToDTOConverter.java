package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICfr;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.web.dto.CfrDTO;

@Component
public class CfrToDTOConverter implements Function<ICfr, CfrDTO> {

	@Override
	public CfrDTO apply(final ICfr entity) {
		final CfrDTO dto = new CfrDTO();
		dto.setId(entity.getId());
		
		ICompany company = entity.getCompany();
		if (company != null) {
			dto.setCompanyName(company.getName());
			dto.setCompanyId(company.getId());
		}
		
		dto.setYear(entity.getYear());

		return dto;
	}

}
