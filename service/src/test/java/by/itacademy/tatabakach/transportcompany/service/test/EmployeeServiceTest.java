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
		assertEquals(entity.getDepartment().getId(), entityFromDb.getDepartment().getId());
		assertEquals(entity.getPosition().getId(), entityFromDb.getPosition().getId());
		assertEquals(entity.getEMail(), entityFromDb.getEMail());
		assertEquals(entity.getPhone(), entityFromDb.getPhone());
		assertEquals(entity.getLogin(), entityFromDb.getLogin());
		assertEquals(entity.getPassword(), entityFromDb.getPassword());
		assertEquals(entity.getSalary(), entityFromDb.getSalary());

	}
	/*
	@Test
	public void createEmployeeWithCompanyTest() {
		final IModel entity = modelService.createEntity();
		entity.setName("model-" + getRandomPrefix());
		entity.setBrand(saveNewBrand());

		final int randomObjectsCount = getRandomObjectsCount();
		final List<IEngine> engines = new ArrayList<>();
		for (int i = 0; i < randomObjectsCount; i++) {
			engines.add(saveNewEngine());
		}
		entity.getEngines().addAll(engines);

		modelService.save(entity);

		final IModel entityFromDb = modelService.getFullInfo(entity.getId());
		final Set<IEngine> enginesFromDb = entityFromDb.getEngines();
		assertEquals(engines.size(), enginesFromDb.size());

		// check that correct (by id) engines were returned
		for (final IEngine engine : engines) {
			boolean found = false;
			for (final IEngine dbEngine : enginesFromDb) {
				if (engine.getId().equals(dbEngine.getId())) {
					found = true;
					break;
				}

			}
			assertTrue(found, "can't find entity:" + engine);
		}
	}
*/
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
