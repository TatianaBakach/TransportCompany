package by.itacademy.tatabakach.transportcompany.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ILocality;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.LocalityFilter;

public class LocalityServiceTest extends AbstractTest{
	
	@Test
	public void testCreate() {
		final ILocality entity = saveNewLocality();

		final ILocality entityFromDb = localityService.getFullInfo(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getRegion().getId(), entityFromDb.getRegion().getId());

	}

	@Test
	public void testGetAll() {
		LocalityFilter filter = new LocalityFilter();
		final long intialCount = localityService.getCount(filter);

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewLocality();
		}
		
		filter.setFetchRegion(true);

		final List<ILocality> allEntities = localityService.find(filter);

		for (final ILocality entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getRegion().getId());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}
	
	@Test
	public void testDelete() {
		final ILocality entity = saveNewLocality();
		localityService.delete(entity.getId());
		assertNull(localityService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewLocality();
		localityService.deleteAll();
		assertEquals(0, localityService.getAll().size());
	}

}
