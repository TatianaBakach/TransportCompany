package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.web.dto.CompanyDTO;

@Component
public class CompanyToDTOConverter implements Function<ICompany, CompanyDTO> {
	
	@Override
	public CompanyDTO apply(final ICompany entity) {
		final CompanyDTO dto = new CompanyDTO();
		dto.setId(entity.getId());
		dto.setCompanyType(entity.getCompanyType());
		dto.setName(entity.getName());
		dto.setPayerRegistrationNumber(entity.getPayerRegistrationNumber());
		dto.setLegalAddress(entity.getLegalAddress());
		dto.setPostAddress(entity.getPostAddress());
		dto.setBankData(entity.getBankData());
		dto.seteMail(entity.getEMail());
		dto.setPhone(entity.getPhone());
		
		return dto;
	}

}
