package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICar;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDriver;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITax;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITransactionCost;
import by.itacademy.tatabakach.transportcompany.service.ICarService;
import by.itacademy.tatabakach.transportcompany.service.ICompanyService;
import by.itacademy.tatabakach.transportcompany.service.IDriverService;
import by.itacademy.tatabakach.transportcompany.service.IEmployeeService;
import by.itacademy.tatabakach.transportcompany.service.IOrderService;
import by.itacademy.tatabakach.transportcompany.service.ITaxService;
import by.itacademy.tatabakach.transportcompany.service.ITransactionCostService;
import by.itacademy.tatabakach.transportcompany.web.dto.OrderDTO;

@Component
public class OrderFromDTOConverter implements Function<OrderDTO, IOrder> {

	@Autowired
	private IOrderService orderService;

	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private ICarService carService;
	
	@Autowired
	private IDriverService driverService;
	
	@Autowired
	private ITransactionCostService transactionCostService;
	
	@Autowired
	private ITaxService taxService;
	
	@Autowired
	private IEmployeeService employeeService;

	@Override
	public IOrder apply(final OrderDTO dto) {
		final IOrder entity = orderService.createEntity();
		entity.setId(dto.getId());
		entity.setNumber(dto.getNumber());
		entity.setDate(dto.getDate());
		
		Integer ourCompanyId = dto.getOurCompanyId();
		if (ourCompanyId != null) {
			ICompany o = companyService.createEntity();
			o.setId(ourCompanyId);
			entity.setOurCompany(o);
		}
		
		Integer carrierId = dto.getCarrierId();
		if (carrierId != null) {
			ICompany cr = companyService.createEntity();
			cr.setId(carrierId);
			entity.setCarrier(cr);
		}
		
		Integer carId = dto.getCarId();
		if (carId != null) {
			ICar car = carService.createEntity();
			car.setId(carId);
			entity.setCar(car);
		}
		
		Integer driverId = dto.getDriverId();
		if (driverId != null) {
			IDriver driver = driverService.createEntity();
			driver.setId(driverId);
			entity.setDriver(driver);
		}
		
		entity.setLoadingMethod(dto.getLoadingMethod());
		entity.setCargoType(dto.getCargoType());
		entity.setCargoWeightVolume(dto.getCargoWeightVolume());
		
		Integer customerCostId = dto.getCustomerCostId();
		if (customerCostId != null) {
			ITransactionCost t = transactionCostService.createEntity();
			t.setId(customerCostId);
			entity.setCustomerCost(t);
		}
		
		entity.setPaidCustomer(dto.getPaidCustomer());
		
		Integer carrierCostId = dto.getCarrierCostId();
		if (carrierCostId != null) {
			ITransactionCost t = transactionCostService.createEntity();
			t.setId(carrierCostId);
			entity.setCarrierCost(t);
		}
		
		entity.setPaidCarrier(dto.getPaidCarrier());
		
		Integer taxId = dto.getTaxId();
		if (taxId != null) {
			ITax tax = taxService.createEntity();
			tax.setId(taxId);
			entity.setTax(tax);
		}
		
		entity.setAdditionalConditions(dto.getAdditionalConditions());
		
		Integer creatorId = dto.getCreatorId();
		if (creatorId != null) {
			IEmployee e = employeeService.createEntity();
			e.setId(creatorId);
			entity.setCreator(e);
		}
		
		entity.setNote(dto.getNote());
		
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
