package by.itacademy.tatabakach.transportcompany.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICar;

public class CarServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final ICar entity = saveNewCar();

		final ICar entityFromDb = carService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertEquals(entity.getModel(), entityFromDb.getModel());
		assertEquals(entity.getNumber(), entityFromDb.getNumber());
		
	}

	@Test
	public void testGetAll() {
		final int intialCount = carService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewCar();
		}

		final List<ICar> allEntities = carService.getAll();

		for (final ICar entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getModel());
			assertNotNull(entityFromDb.getNumber());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final ICar entity = saveNewCar();
		carService.delete(entity.getId());
		assertNull(carService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewCar();
		carService.deleteAll();
		assertEquals(0, carService.getAll().size());
	}

}
