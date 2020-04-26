package by.itacademy.tatabakach.transportcompany.service.test;

//import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICfr;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CfrFilter;

public class CftServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final ICfr entity = saveNewCfr();

		final ICfr entityFromDb = cfrService.getFullInfo(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertEquals(entity.getCompany().getId(), entityFromDb.getCompany().getId());
		assertEquals(entity.getYear(), entityFromDb.getYear());

	}

	@Test
	public void testUnique() {
		ICfr entity1 = cfrService.createEntity();

		ICompany newCompany = saveNewCompany();
		
		entity1.setCompany(newCompany);
		entity1.setYear(2020);

		cfrService.save(entity1);

		try {
			ICfr entity2 = cfrService.createEntity();
			entity2.setCompany(newCompany);
			entity2.setYear(2020);
			cfrService.save(entity2);

		//	fail("object can't be saved with the same unique key");

		} catch (Exception e) {
			// ok, constraint exists
		}

	}

	@Test
	public void testGetAll() {
		CfrFilter filter = new CfrFilter();
		final long intialCount = cfrService.getCount(filter);

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewCfr();
		}

		final List<ICfr> allEntities = cfrService.find(filter);

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
