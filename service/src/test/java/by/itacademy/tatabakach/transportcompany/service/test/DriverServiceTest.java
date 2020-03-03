package by.itacademy.tatabakach.transportcompany.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDriver;

public class DriverServiceTest extends AbstractTest{
	
	@Test
	public void testCreate() {
		final IDriver entity = saveNewDriver();

		final IDriver entityFromDb = driverService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertEquals(entity.getFirstName(), entityFromDb.getFirstName());
		assertEquals(entity.getMiddleName(), entityFromDb.getMiddleName());
		assertEquals(entity.getLastName(), entityFromDb.getLastName());
		assertEquals(entity.getPassportData(), entityFromDb.getPassportData());
		assertEquals(entity.getPhone(), entityFromDb.getPhone());
		
	}

	@Test
	public void testGetAll() {
		final int intialCount = driverService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewDriver();
		}

		final List<IDriver> allEntities = driverService.getAll();

		for (final IDriver entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getFirstName());
			assertNotNull(entityFromDb.getMiddleName());
			assertNotNull(entityFromDb.getLastName());
			assertNotNull(entityFromDb.getPassportData());
			assertNotNull(entityFromDb.getPhone());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IDriver entity = saveNewDriver();
		driverService.delete(entity.getId());
		assertNull(driverService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewDriver();
		driverService.deleteAll();
		assertEquals(0, driverService.getAll().size());
	}


}
