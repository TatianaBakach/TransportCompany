package by.itacademy.tatabakach.transportcompany.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRegion;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.RegionFilter;

public class RegionServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final IRegion entity = saveNewRegion();

		final IRegion entityFromDb = regionService.getFullInfo(entity.getId()); // fixme full load

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getCountry().getId(), entityFromDb.getCountry().getId());

	}

	@Test
	public void testGetAll() {
		RegionFilter filter = new RegionFilter();
		final long intialCount = regionService.getCount(filter);

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewRegion();
		}
		
		filter.setFetchCountry(true);

		final List<IRegion> allEntities = regionService.find(filter);

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
