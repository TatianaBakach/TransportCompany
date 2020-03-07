package by.itacademy.tatabakach.transportcompany.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRegion;

public class RegionServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final IRegion entity = saveNewRegion();

		final IRegion entityFromDb = regionService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getCountry().getId(), entityFromDb.getCountry().getId());

	}

	@Test
	public void testGetAll() {
		final int intialCount = regionService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewRegion();
		}

		final List<IRegion> allEntities = regionService.getAll();

		for (final IRegion entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getCountry().getId());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}
	
	@Test
	public void testDelete() {
		final IRegion entity = saveNewRegion();
		regionService.delete(entity.getId());
		assertNull(regionService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewRegion();
		regionService.deleteAll();
		assertEquals(0, regionService.getAll().size());
	}

}
