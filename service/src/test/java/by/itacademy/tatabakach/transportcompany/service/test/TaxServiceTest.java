package by.itacademy.tatabakach.transportcompany.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITax;

public class TaxServiceTest extends AbstractTest {
	
	@Test
	public void testCreate() {
		final ITax entity = saveNewTax();

		final ITax entityFromDb = taxService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getRate(), entityFromDb.getRate());

	}

	@Test
	public void testGetAll() {
		final int intialCount = taxService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewTax();
		}

		final List<ITax> allEntities = taxService.getAll();

		for (final ITax entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getRate());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final ITax entity = saveNewTax();
		taxService.delete(entity.getId());
		assertNull(taxService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewTax();
		taxService.deleteAll();
		assertEquals(0, taxService.getAll().size());
	}

}
