package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICar;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDriver;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITax;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITransactionCost;
import by.itacademy.tatabakach.transportcompany.web.dto.OrderDTO;

@Component
public class OrderToDTOConverter implements Function<IOrder, OrderDTO> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderToDTOConverter.class);

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

		ITransactionCost customerCost = entity.getCustomerCost();
		if (customerCost != null) {
			dto.setCustomerCostName(String.format("%s %s", customerCost.getCurrency(),
					customerCost.getAmount()));
			dto.setCustomerCostId(customerCost.getId());
		}
		
		dto.setPaidCustomer(entity.getPaidCustomer());

		ITransactionCost carrierCost = entity.getCarrierCost();
		if (carrierCost != null) {
			dto.setCarrierCostName(String.format("%s %s", carrierCost.getCurrency(),
					carrierCost.getAmount()));
			dto.setCarrierCostId(carrierCost.getId());
		}
		
		dto.setPaidCarrier(entity.getPaidCarrier());
		
		ITax tax = entity.getTax();
		if (tax != null) {
			dto.setTaxName(tax.getName());
			dto.setTaxId(tax.getId());
		}

		dto.setAdditionalConditions(entity.getAdditionalConditions());

		IEmployee creator = entity.getCreator();
		if (creator != null) {
			dto.setCreatorName(String.format("%s %s", creator.getFirstName(), creator.getLastName()));
			dto.setCreatorId(creator.getId());
		}
		
		dto.setNote(entity.getNote());
		
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
