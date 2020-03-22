package by.itacademy.tatabakach.transportcompany.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderReward;

public class OrderRewardServiceTest extends AbstractTest {
	
	@Test
	public void testCreate() {
		final IOrderReward entity = saveNewOrderReward();

		final IOrderReward entityFromDb = orderRewardService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertEquals(entity.getOrder().getId(), entityFromDb.getOrder().getId());
		assertEquals(entity.getEmployee().getId(), entityFromDb.getEmployee().getId());
		assertEquals(entity.getRewardType(), entityFromDb.getRewardType());
		assertEquals(entity.getOrderRewardPercent().getId(), entityFromDb.getOrderRewardPercent().getId());
		assertEquals(entity.getAdditionalExpenses(), entityFromDb.getAdditionalExpenses());
		assertEquals(entity.getAmount(), entityFromDb.getAmount());
		assertEquals(entity.getPaymentDate(), entityFromDb.getPaymentDate());
		assertEquals(entity.getRewardIssued(), entityFromDb.getRewardIssued());
		assertEquals(entity.getNote(), entityFromDb.getNote());
		
	}

	@Test
	public void testGetAll() {
		final int intialCount = orderRewardService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewOrderReward();
		}

		final List<IOrderReward> allEntities = orderRewardService.getAll();

		for (final IOrderReward entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getOrder());
			assertNotNull(entityFromDb.getEmployee());
			assertNotNull(entityFromDb.getRewardType());
			assertNotNull(entityFromDb.getOrderRewardPercent());
			assertNotNull(entityFromDb.getAdditionalExpenses());
			assertNotNull(entityFromDb.getAmount());
			assertNotNull(entityFromDb.getPaymentDate());
			assertNotNull(entityFromDb.getRewardIssued());
			assertNotNull(entityFromDb.getNote());
			
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IOrderReward entity = saveNewOrderReward();
		orderRewardService.delete(entity.getId());
		assertNull(orderRewardService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewOrderReward();
		orderRewardService.deleteAll();
		assertEquals(0, orderRewardService.getAll().size());
	}

}
