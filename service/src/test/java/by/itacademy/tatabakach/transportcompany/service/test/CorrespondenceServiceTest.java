package by.itacademy.tatabakach.transportcompany.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICorrespondence;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CorrespondenceFilter;

public class CorrespondenceServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final ICorrespondence entity = saveNewCorrespondence();

		final ICorrespondence entityFromDb = correspondenceService.getFullInfo(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertEquals(entity.getCorrespondenceType(), entityFromDb.getCorrespondenceType());
		assertEquals(entity.getOrder().getId(), entityFromDb.getOrder().getId());
		assertEquals(entity.getCompany().getId(), entityFromDb.getCompany().getId());
		assertEquals(entity.getDate(), entityFromDb.getDate());
		assertEquals(entity.getContent(), entityFromDb.getContent());
		assertEquals(entity.getNote(), entityFromDb.getNote());

	}

	@Test
	public void testGetAll() {
		CorrespondenceFilter filter = new CorrespondenceFilter();
		final long intialCount = correspondenceService.getCount(filter);

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewCorrespondence();
		}

		final List<ICorrespondence> allEntities = correspondenceService.find(filter);

		for (final ICorrespondence entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCorrespondenceType());
			assertNotNull(entityFromDb.getOrder());
			assertNotNull(entityFromDb.getCompany());
			assertNotNull(entityFromDb.getDate());
			assertNotNull(entityFromDb.getContent());
			assertNotNull(entityFromDb.getNote());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final ICorrespondence entity = saveNewCorrespondence();
		correspondenceService.delete(entity.getId());
		assertNull(correspondenceService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewCorrespondence();
		correspondenceService.deleteAll();
		assertEquals(0, correspondenceService.getAll().size());
	}

}
