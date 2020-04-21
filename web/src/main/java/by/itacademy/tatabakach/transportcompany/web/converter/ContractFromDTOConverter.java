package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IContract;
import by.itacademy.tatabakach.transportcompany.service.ICompanyService;
import by.itacademy.tatabakach.transportcompany.service.IContractService;
import by.itacademy.tatabakach.transportcompany.web.dto.ContractDTO;

@Component
public class ContractFromDTOConverter implements Function<ContractDTO, IContract> {

	@Autowired
	private IContractService contractService;

	@Autowired
	private ICompanyService companyService;

	@Override
	public IContract apply(final ContractDTO dto) {
		final IContract entity = contractService.createEntity();
		entity.setId(dto.getId());
		entity.setNumber(dto.getNumber());

		Integer ourCompanyId = dto.getOurCompanyId();
		if (ourCompanyId != null) {
			ICompany o = companyService.createEntity();
			o.setId(ourCompanyId);
			entity.setOurCompany(o);;
		}
		
		Integer companyId = dto.getCompanyId();
		if (companyId != null) {
			ICompany c = companyService.createEntity();
			c.setId(companyId);
			entity.setCompany(c);;
		}
		
		entity.setDate(dto.getDate());

		return entity;
	}
}
