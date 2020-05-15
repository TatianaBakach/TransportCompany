package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IAddress;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.service.IAddressService;
import by.itacademy.tatabakach.transportcompany.service.ICompanyService;
import by.itacademy.tatabakach.transportcompany.service.IEmployeeService;
import by.itacademy.tatabakach.transportcompany.web.dto.CompanyDTO;

@Component
public class CompanyFromDTOConverter implements Function<CompanyDTO, ICompany> {

	@Autowired
	private IAddressService addressService;
	
	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private ICompanyService companyService;

	@Override
	public ICompany apply(final CompanyDTO dto) {
		final ICompany entity = companyService.createEntity();
		entity.setId(dto.getId());
		entity.setId(dto.getId());
		entity.setCompanyType(dto.getCompanyType());
		entity.setName(dto.getName());
		entity.setPayerRegistrationNumber(dto.getPayerRegistrationNumber());
		
		Integer legalAddressId = dto.getLegalAddressId();
		if (legalAddressId != null) {
			IAddress l = addressService.createEntity();
			l.setId(legalAddressId);
			entity.setLegalAddress(l);
		}
		
		Integer postAddressId = dto.getPostAddressId();
		if (postAddressId != null) {
			IAddress p = addressService.createEntity();
			p.setId(postAddressId);
			entity.setPostAddress(p);
		}
		
		entity.setBankData(dto.getBankData());
		entity.setMail(dto.getMail());
		entity.setPhone(dto.getPhone());
		
		Integer creatorId = dto.getCreatorId();
		if (creatorId != null) {
			IEmployee e = employeeService.createEntity();
			e.setId(creatorId);
			entity.setCreator(e);
		}
		
		final Set<Integer> employeeIds = dto.getEmployeeIds();
        if (CollectionUtils.isNotEmpty(employeeIds)) {
            entity.setEmployees(employeeIds.stream().map((id) -> {
                final IEmployee employee = employeeService.createEntity();
                employee.setId(id);
                return employee;
            }).collect(Collectors.toSet()));
        }

		return entity;
	}
}
