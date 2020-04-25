package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IAddress;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICar;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDriver;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITransactionCost;
import by.itacademy.tatabakach.transportcompany.web.dto.CompanyDTO;
import by.itacademy.tatabakach.transportcompany.web.dto.OrderDTO;

@Component
public class OrderToDTOConverter implements Function<IOrder, OrderDTO> {
	
	@Override
	public OrderDTO apply(final IOrder entity) {
		final OrderDTO dto = new OrderDTO();
		dto.setId(entity.getId());
		dto.setNumber(entity.getNumber());
		dto.setDate(entity.getDate());
		
		ICompany ourCompany = entity.getOurCompany();
		if (ourCompany != null) {
			dto.setOurCompanyName(ourCompany.getName());
			dto.setOurCompanyId(ourCompany.getId());
		}
		
		ICompany customer = entity.getCustomer();
		if (customer != null) {
			dto.setCustomerName(customer.getName());
			dto.setCustomerId(customer.getId());
		}
		
		ICompany carrier = entity.getCarrier();
		if (carrier != null) {
			dto.setCarrierName(carrier.getName());
			dto.setCarrierId(carrier.getId());
		}
		
		ICar car = entity.getCar();
		if (car != null) {
			dto.setCarName(String.format("%s %s", car.getModel(), car.getNumber()));
			dto.setCarId(car.getId());
		}
		
		IDriver driver = entity.getDriver();
		if (driver != null) {
			dto.setDriverName(String.format("%s %s", driver.getLastName(), driver.getFirstName()));
			dto.setDriverId(driver.getId());
		}

		
		dto.setLoadingMethod(entity.getLoadingMethod());
		dto.setCargoType(entity.getCargoType());
		dto.setCargoWeightVolume(entity.getCargoWeightVolume());
		
		/*
		 * ITransactionCost customerCost = entity.getCustomerCost(); if (customerCost !=
		 * null) { dto.setCustomerCostName(String.format("%s %s %s", customerCost.,
		 * legalAddress.getExactAddress()));
		 * dto.setLegalAddressId(legalAddress.getId()); }
		 * 
		 * IAddress postAddress = entity.getPostAddress(); if (postAddress != null) {
		 * dto.setPostAddressName(postAddress.getExactAddress());
		 * dto.setPostAddressId(postAddress.getId()); }
		 * 
		 * dto.setBankData(entity.getBankData()); dto.setMail(entity.getMail());
		 * dto.setPhone(entity.getPhone());
		 * 
		 * IEmployee creator = entity.getCreator(); if (creator != null) {
		 * dto.setCreatorName(String.format("%s %s", creator.getFirstName(),
		 * creator.getLastName())); dto.setCreatorId(creator.getId()); }
		 */
		return dto;
	}
	
}
