package by.itacademy.tatabakach.transportcompany.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

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
		assertEquals(entity.getMail(), entityFromDb.getMail());
		assertEquals(entity.getPhone(), entityFromDb.getPhone());
		assertEquals(entity.getLogin(), entityFromDb.getLogin());
		assertEquals(entity.getPassword(), entityFromDb.getPassword());
		assertEquals(entity.getSalary(), entityFromDb.getSalary());

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
			assertNotNull(entityFromDb.getMail());
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
