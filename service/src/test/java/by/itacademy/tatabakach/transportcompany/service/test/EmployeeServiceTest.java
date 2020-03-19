package by.itacademy.tatabakach.transportcompany.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.Department;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.Position;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;

public class EmployeeServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final IEmployee entity = saveNewEmployee();

		final IEmployee entityFromDb = employeeService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertEquals(entity.getFirstName(), entityFromDb.getFirstName());
		assertEquals(entity.getMiddleName(), entityFromDb.getMiddleName());
		assertEquals(entity.getLastName(), entityFromDb.getLastName());
		assertEquals(entity.getDepartment(), entityFromDb.getDepartment());
		assertEquals(entity.getPosition(), entityFromDb.getPosition());
		assertEquals(entity.getEMail(), entityFromDb.getEMail());
		assertEquals(entity.getPhone(), entityFromDb.getPhone());
		assertEquals(entity.getLogin(), entityFromDb.getLogin());
		assertEquals(entity.getPassword(), entityFromDb.getPassword());
		assertEquals(entity.getSalary(), entityFromDb.getSalary());

	}
	
	@Test
	public void createEmployeeWithCompanyTest() {
		final IEmployee entity = employeeService.createEntity();
		entity.setFirstName("first_name-" + getRandomPrefix());
		entity.setMiddleName("middle_name-" + getRandomPrefix());
		entity.setLastName("last_name-" + getRandomPrefix());
		entity.setDepartment(getRandomFromArray(Department.values()));
		entity.setPosition(getRandomFromArray(Position.values()));
		entity.setEMail("eMail-" + getRandomPrefix());
		entity.setPhone("phone-" + getRandomPrefix());
		entity.setLogin("login-" + getRandomPrefix());
		entity.setPassword("password-" + getRandomPrefix());
		entity.setSalary(getRandomBigDecimal(2));

		final int randomObjectsCount = getRandomObjectsCount();
		final List<ICompany> companies = new ArrayList<>();
		for (int i = 0; i < randomObjectsCount; i++) {
			companies.add(saveNewCompany());
		}
		entity.getCompanies().addAll(companies);

		employeeService.save(entity);

		final IEmployee entityFromDb = employeeService.getFullInfo(entity.getId());
		final Set<ICompany> companiesFromDb = entityFromDb.getCompanies();
		assertEquals(companies.size(), companiesFromDb.size());

		// check that correct (by id) engines were returned
		for (final ICompany company : companies) {
			boolean found = false;
			for (final ICompany dbCompany : companiesFromDb) {
				if (company.getId().equals(dbCompany.getId())) {
					found = true;
					break;
				}

			}
			assertTrue(found, "can't find entity:" + company);
		}
	}

	@Test
	public void testGetAll() {
		final int intialCount = employeeService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewEmployee();
		}

		final List<IEmployee> allEntities = employeeService.getAll();

		for (final IEmployee entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getFirstName());
			assertNotNull(entityFromDb.getMiddleName());
			assertNotNull(entityFromDb.getLastName());
			assertNotNull(entityFromDb.getDepartment());
			assertNotNull(entityFromDb.getPosition());
			assertNotNull(entityFromDb.getEMail());
			assertNotNull(entityFromDb.getPhone());
			assertNotNull(entityFromDb.getLogin());
			assertNotNull(entityFromDb.getPassword());
			assertNotNull(entityFromDb.getSalary());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IEmployee entity = saveNewEmployee();
		employeeService.delete(entity.getId());
		assertNull(employeeService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewEmployee();
		employeeService.deleteAll();
		assertEquals(0, employeeService.getAll().size());
	}

}
