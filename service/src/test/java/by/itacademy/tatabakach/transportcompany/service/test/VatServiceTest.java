package by.itacademy.tatabakach.transportcompany.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IVat;

public class VatServiceTest extends AbstractTest {
	
	@Test
	public void testCreate() {
		final IVat entity = saveNewVat();

		final IVat entityFromDb = vatService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getRate(), entityFromDb.getRate());

	}

	@Test
	public void testGetAll() {
		final int intialCount = vatService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewVat();
		}

		final List<IVat> allEntities = vatService.getAll();

		for (final IVat entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getRate());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IVat entity = saveNewVat();
		vatService.delete(entity.getId());
		assertNull(vatService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewVat();
		vatService.deleteAll();
		assertEquals(0, vatService.getAll().size());
	}

}
