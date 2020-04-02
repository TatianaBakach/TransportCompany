package by.itacademy.dzhivushko.cars.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import by.itacademy.dzhivushko.cars.dao.api.entity.table.IBrand;

public class BrandServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final IBrand entity = saveNewBrand();

		final IBrand entityFromDb = brandService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getName(), entityFromDb.getName());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}

	@Test
	public void testGetAll() {
		final int intialCount = brandService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewBrand();
		}

		final List<IBrand> allEntities = brandService.getAll();

		for (final IBrand entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IBrand entity = saveNewBrand();
		brandService.delete(entity.getId());
		assertNull(brandService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewBrand();
		brandService.deleteAll();
		assertEquals(0, brandService.getAll().size());
	}
}
