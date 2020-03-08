package by.itacademy.tatabakach.transportcompany.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDistrict;

public class DistrictServiceTest extends AbstractTest {
	
	@Test
	public void testCreate() {
		final IDistrict entity = saveNewDistrict();

		final IDistrict entityFromDb = districtService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getRegion().getId(), entityFromDb.getRegion().getId());

	}

	@Test
	public void testGetAll() {
		final int intialCount = districtService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewDistrict();
		}

		final List<IDistrict> allEntities = districtService.getAll();

		for (final IDistrict entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getRegion().getId());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}
	
	@Test
	public void testDelete() {
		final IDistrict entity = saveNewDistrict();
		districtService.delete(entity.getId());
		assertNull(districtService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewDistrict();
		districtService.deleteAll();
		assertEquals(0, districtService.getAll().size());
	}

}
