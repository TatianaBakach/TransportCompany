package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IContract;
import by.itacademy.tatabakach.transportcompany.web.dto.ContractDTO;

@Component
public class ContractToDTOConverter implements Function<IContract, ContractDTO> {

	@Override
	public ContractDTO apply(final IContract entity) {
		final ContractDTO dto = new ContractDTO();
		dto.setId(entity.getId());
		dto.setNumber(entity.getNumber());
		
		ICompany ourCompany = entity.getOurCompany();
		if (ourCompany != null) {
			dto.setOurCompanyName(ourCompany.getName());
			dto.setOurCompanyId(ourCompany.getId());
		}
		
		ICompany company = entity.getCompany();
		if (company != null) {
			dto.setCompanyName(company.getName());
			dto.setCompanyId(company.getId());
		}
		
		dto.setDate(entity.getDate());

		return dto;
	}

}
