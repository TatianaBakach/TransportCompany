package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IAddress;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
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
		
		IAddress legalAddress = entity.getLegalAddress();
		if (legalAddress != null) {
			dto.setLegalAddressName(String.format("%s %s %s", legalAddress.getPostcode(), legalAddress.getLocality(), legalAddress.getExactAddress()));
			dto.setLegalAddressId(legalAddress.getId());
		}

		IAddress postAddress = entity.getPostAddress();
		if (postAddress != null) {
			dto.setPostAddressName(postAddress.getExactAddress());
			dto.setPostAddressId(postAddress.getId());
		}
		
		dto.setBankData(entity.getBankData());
		dto.setMail(entity.getMail());
		dto.setPhone(entity.getPhone());
		
		IEmployee creator = entity.getCreator();
		if (creator != null) {
			dto.setCreatorName(String.format("%s %s", creator.getFirstName(), creator.getLastName()));
			dto.setCreatorId(creator.getId());
		}
		
		return dto;
	}
	
}
