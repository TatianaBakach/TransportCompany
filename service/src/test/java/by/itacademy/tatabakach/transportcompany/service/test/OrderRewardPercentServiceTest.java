package by.itacademy.tatabakach.transportcompany.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderRewardPercent;

public class OrderRewardPercentServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final IOrderRewardPercent entity = saveNewOrderRewardPercent();

		final IOrderRewardPercent entityFromDb = orderRewardPercentService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getPercent(), entityFromDb.getPercent());

	}

	@Test
	public void testGetAll() {
		final int intialCount = orderRewardPercentService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewOrderRewardPercent();
		}

		final List<IOrderRewardPercent> allEntities = orderRewardPercentService.getAll();

		for (final IOrderRewardPercent entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getPercent());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IOrderRewardPercent entity = saveNewOrderRewardPercent();
		orderRewardPercentService.delete(entity.getId());
		assertNull(orderRewardPercentService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewOrderRewardPercent();
		orderRewardPercentService.deleteAll();
		assertEquals(0, orderRewardPercentService.getAll().size());
	}

}
