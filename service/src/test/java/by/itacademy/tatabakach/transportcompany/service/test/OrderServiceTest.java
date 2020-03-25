package by.itacademy.tatabakach.transportcompany.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.LoadingMethod;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.OrderFilter;

public class OrderServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final IOrder entity = saveNewOrder();

		final IOrder entityFromDb = orderService.getFullInfo(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertEquals(entity.getNumber(), entityFromDb.getNumber());
		assertEquals(entity.getDate(), entityFromDb.getDate());
		assertEquals(entity.getOurCompany().getId(), entityFromDb.getOurCompany().getId());
		assertEquals(entity.getCustomer().getId(), entityFromDb.getCustomer().getId());
		assertEquals(entity.getCarrier().getId(), entityFromDb.getCarrier().getId());
		assertEquals(entity.getCar().getId(), entityFromDb.getCar().getId());
		assertEquals(entity.getDriver().getId(), entityFromDb.getDriver().getId());
		assertEquals(entity.getLoadingMethod(), entityFromDb.getLoadingMethod());
		assertEquals(entity.getCargoType(), entityFromDb.getCargoType());
		assertEquals(entity.getCargoWeightVolume(), entityFromDb.getCargoWeightVolume());
		assertEquals(entity.getCustomerCost().getId(), entityFromDb.getCustomerCost().getId());
		assertEquals(entity.getPaidCustomer(), entityFromDb.getPaidCustomer());
		assertEquals(entity.getCarrierCost().getId(), entityFromDb.getCarrierCost().getId());
		assertEquals(entity.getPaidCarrier(), entityFromDb.getPaidCarrier());
		assertEquals(entity.getTax().getId(), entityFromDb.getTax().getId());
		assertEquals(entity.getAdditionalConditions(), entityFromDb.getAdditionalConditions());
		assertEquals(entity.getCreator().getId(), entityFromDb.getCreator().getId());
		assertEquals(entity.getNote(), entityFromDb.getNote());
	
		
	}

	@Test
	public void createOrderWithEmployeeTest() {
		final IOrder entity = orderService.createEntity();
		entity.setNumber("#-" + getRandomPrefix());
		entity.setDate(getRandomDate());
		entity.setOurCompany(saveNewCompany());
		entity.setCustomer(saveNewCompany());
		entity.setCarrier(saveNewCompany());
		entity.setCar(saveNewCar());
		entity.setDriver(saveNewDriver());
		entity.setLoadingMethod(getRandomFromArray(LoadingMethod.values()));
		entity.setCargoType("cargoType-" + getRandomPrefix());
		entity.setCargoWeightVolume("cargoWeightVolume-" + getRandomPrefix());
		entity.setCustomerCost(saveNewTransactionCost());
		entity.setPaidCustomer(false);
		entity.setCarrierCost(saveNewTransactionCost());
		entity.setPaidCarrier(false);
		entity.setTax(saveNewTax());
		entity.setAdditionalConditions("additionalConditions-" + getRandomPrefix());
		entity.setCreator(saveNewEmployee());
		entity.setNote("note" + getRandomPrefix());

		final int randomObjectsCount = getRandomObjectsCount();
		final List<IEmployee> employees = new ArrayList<>();
		for (int i = 0; i < randomObjectsCount; i++) {
			employees.add(saveNewEmployee());
		}
		entity.getEmployees().addAll(employees);

		orderService.save(entity);

		final IOrder entityFromDb = orderService.getFullInfo(entity.getId());
		final Set<IEmployee> employeesFromDb = entityFromDb.getEmployees();
		assertEquals(employees.size(), employeesFromDb.size());

		// check that correct (by id) engines were returned
		for (final IEmployee employee : employees) {
			boolean found = false;
			for (final IEmployee dbEmployee : employeesFromDb) {
				if (employee.getId().equals(dbEmployee.getId())) {
					found = true;
					break;
				}

			}
			assertTrue(found, "can't find entity:" + employee);
		}
	}

	@Test
	public void testGetAll() {
		OrderFilter filter = new OrderFilter();
		final long intialCount = orderService.getCount(filter);

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewOrder();
		}

		final List<IOrder> allEntities = orderService.find(filter);

		for (final IOrder entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getNumber());
			assertNotNull(entityFromDb.getDate());
			assertNotNull(entityFromDb.getOurCompany());
			assertNotNull(entityFromDb.getCustomer());
			assertNotNull(entityFromDb.getCarrier());
			assertNotNull(entityFromDb.getCar());
			assertNotNull(entityFromDb.getDriver());
			assertNotNull(entityFromDb.getLoadingMethod());
			assertNotNull(entityFromDb.getCargoType());
			assertNotNull(entityFromDb.getCargoWeightVolume());
			assertNotNull(entityFromDb.getCustomerCost());
			assertNotNull(entityFromDb.getPaidCustomer());
			assertNotNull(entityFromDb.getCarrierCost());
			assertNotNull(entityFromDb.getPaidCarrier());
			assertNotNull(entityFromDb.getTax());
			assertNotNull(entityFromDb.getAdditionalConditions());
			assertNotNull(entityFromDb.getCreator());
			assertNotNull(entityFromDb.getNote());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IOrder entity = saveNewOrder();
		orderService.delete(entity.getId());
		assertNull(orderService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewOrder();
		orderService.deleteAll();
		assertEquals(0, orderService.getAll().size());
	}
	
}
