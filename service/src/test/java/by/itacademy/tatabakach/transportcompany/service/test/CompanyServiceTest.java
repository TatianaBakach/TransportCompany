package by.itacademy.tatabakach.transportcompany.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.CompanyType;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CompanyFilter;

public class CompanyServiceTest extends AbstractTest {
	
	@Test
	public void testCreate() {
		final ICompany entity = saveNewCompany();

		final ICompany entityFromDb = companyService.getFullInfo(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCompanyType());
		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getPayerRegistrationNumber(), entityFromDb.getPayerRegistrationNumber());
		assertEquals(entity.getLegalAddress().getId(), entityFromDb.getLegalAddress().getId());
		assertEquals(entity.getPostAddress().getId(), entityFromDb.getPostAddress().getId());
		assertEquals(entity.getBankData(), entityFromDb.getBankData());
		assertEquals(entity.getEMail(), entityFromDb.getEMail());
		assertEquals(entity.getPhone(), entityFromDb.getPhone());
		assertEquals(entity.getCreator().getId(), entityFromDb.getCreator().getId());

	}

	@Test
	public void testGetAll() {
		CompanyFilter filter = new CompanyFilter();
		final long intialCount = companyService.getCount(filter);

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewCompany();
		}

		final List<ICompany> allEntities = companyService.getAll();

		for (final ICompany entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCompanyType());
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getPayerRegistrationNumber());
			assertNotNull(entityFromDb.getLegalAddress());
			assertNotNull(entityFromDb.getPostAddress());
			assertNotNull(entityFromDb.getBankData());
			assertNotNull(entityFromDb.getEMail());
			assertNotNull(entityFromDb.getPhone());
			assertNotNull(entityFromDb.getCreator());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final ICompany entity = saveNewCompany();
		companyService.delete(entity.getId());
		assertNull(companyService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewCompany();
		companyService.deleteAll();
		assertEquals(0, companyService.getAll().size());
	}
	
	@Test
	public void createCompanyWithEmployeeTest() {
		final ICompany entity = companyService.createEntity();
		entity.setCompanyType(getRandomFromArray(CompanyType.values()));
		entity.setName("name-" + getRandomPrefix());
		entity.setPayerRegistrationNumber("rpn-" + getRandomPrefix());
		entity.setLegalAddress(saveNewAddress());
		entity.setPostAddress(saveNewAddress());
		entity.setBankData("bankData-" + getRandomPrefix());
		entity.setEMail("eMail-" + getRandomPrefix());
		entity.setPhone("phone-" + getRandomPrefix());
		entity.setCreator(saveNewEmployee());

		final int randomObjectsCount = getRandomObjectsCount();
		final List<IEmployee> employees = new ArrayList<>();
		for (int i = 0; i < randomObjectsCount; i++) {
			employees.add(saveNewEmployee());
		}
		entity.getEmployees().addAll(employees);

		companyService.save(entity);

		final ICompany entityFromDb = companyService.getFullInfo(entity.getId());
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

}
