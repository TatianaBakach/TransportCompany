package by.itacademy.tatabakach.transportcompany.service.test;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICfr;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Company;

public class CftServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final ICfr entity = saveNewCfr();

		final ICfr entityFromDb = cfrService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertEquals(entity.getCompany().getId(), entityFromDb.getCompany().getId());
		assertEquals(entity.getYear(), entityFromDb.getYear());

	}
/*
	@Test
	public void testUnique() {
		ICfr entity = cfrService.createEntity();

		entity.setCompany(saveNewCompany());
		entity.setYear(2010);

		cfrService.save(entity);

		try {
			entity.setCompany(saveNewCompany());
			entity.setYear(2010);

			entity.setId(null);
			cfrService.save(entity);

			fail("object can't be saved with the same unique key");

		} catch (Exception e) {
			// ok, constraint exists
		}

	}
*/
	@Test
	public void testGetAll() {
		final int intialCount = cfrService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewCfr();
		}

		final List<ICfr> allEntities = cfrService.getAll();

		for (final ICfr entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCompany());
			assertNotNull(entityFromDb.getYear());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final ICfr entity = saveNewCfr();
		cfrService.delete(entity.getId());
		assertNull(cfrService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewCfr();
		cfrService.deleteAll();
		assertEquals(0, cfrService.getAll().size());
	}

}
