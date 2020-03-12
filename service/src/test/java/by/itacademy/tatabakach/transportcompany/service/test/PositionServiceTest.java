package by.itacademy.tatabakach.transportcompany.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IPosition;

public class PositionServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final IPosition entity = saveNewPosition();

		final IPosition entityFromDb = positionService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertEquals(entity.getName(), entityFromDb.getName());

	}

	@Test
	public void testGetAll() {
		final int intialCount = positionService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewPosition();
		}

		final List<IPosition> allEntities = positionService.getAll();

		for (final IPosition entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getName());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IPosition entity = saveNewPosition();
		positionService.delete(entity.getId());
		assertNull(positionService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewPosition();
		positionService.deleteAll();
		assertEquals(0, positionService.getAll().size());
	}

}
