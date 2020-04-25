package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICfr;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.service.ICfrService;
import by.itacademy.tatabakach.transportcompany.service.ICompanyService;
import by.itacademy.tatabakach.transportcompany.web.dto.CfrDTO;

@Component
public class CfrFromDTOConverter implements Function<CfrDTO, ICfr> {

	@Autowired
	private ICfrService cfrService;

	@Autowired
	private ICompanyService companyService;

	@Override
	public ICfr apply(final CfrDTO dto) {
		final ICfr entity = cfrService.createEntity();
		entity.setId(dto.getId());
		
		Integer companyId = dto.getCompanyId();
		if (companyId != null) {
			ICompany c = companyService.createEntity();
			c.setId(companyId);
			entity.setCompany(c);;
		}
		
		entity.setYear(dto.getYear());
		
		return entity;
	}
}
