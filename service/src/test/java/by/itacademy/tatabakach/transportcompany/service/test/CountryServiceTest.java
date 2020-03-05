package by.itacademy.tatabakach.transportcompany.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICountry;

public class CountryServiceTest extends AbstractTest{
	
	@Test
	public void testCreate() {
		final ICountry entity = saveNewCountry();

		final ICountry entityFromDb = countryService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertEquals(entity.getName(), entityFromDb.getName());

	}

	@Test
	public void testGetAll() {
		final int intialCount = countryService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewCountry();
		}

		final List<ICountry> allEntities = countryService.getAll();

		for (final ICountry entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getName());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final ICountry entity = saveNewCountry();
		countryService.delete(entity.getId());
		assertNull(countryService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewCountry();
		countryService.deleteAll();
		assertEquals(0, countryService.getAll().size());
	}

}
