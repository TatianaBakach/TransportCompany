package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IAddress;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.web.dto.CompanyDTO;

@Component
public class CompanyToDTOConverter implements Function<ICompany, CompanyDTO> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyToDTOConverter.class);
	
	@Override
	public CompanyDTO apply(final ICompany entity) {
		final CompanyDTO dto = new CompanyDTO();
		dto.setId(entity.getId());
		dto.setCompanyType(entity.getCompanyType());
		dto.setName(entity.getName());
		dto.setPayerRegistrationNumber(entity.getPayerRegistrationNumber());
		
		IAddress legalAddress = entity.getLegalAddress();
		if (legalAddress != null) {
			dto.setLegalAddressName(String.format("%s %s %s", legalAddress.getPostcode(), legalAddress.getLocality().getName(), legalAddress.getExactAddress()));
			dto.setLegalAddressId(legalAddress.getId());
		}

		IAddress postAddress = entity.getPostAddress();
		if (postAddress != null) {
			dto.setPostAddressName(String.format("%s %s %s", postAddress.getPostcode(), postAddress.getLocality().getName(), postAddress.getExactAddress()));
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
		
		 try {
	            final Set<IEmployee> employees = entity.getEmployees();
	            if (employees != null) {
	                dto.setEmployeeIds(employees.stream().map(IEmployee::getId).collect(Collectors.toSet()));
	            }
	        } catch (final Exception e) {
	            LOGGER.warn("ignore conversion of 'employees' collection because of:" + e.getMessage());
	        }
		
		return dto;
	}
	
}
