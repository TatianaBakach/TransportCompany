package by.itacademy.tatabakach.transportcompany.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IPayment;

public class PaymentServiceTest extends AbstractTest {
	
	@Test
	public void testCreate() {
		final IPayment entity = saveNewPayment();

		final IPayment entityFromDb = paymentService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertEquals(entity.getDate(), entityFromDb.getDate());
		assertEquals(entity.getOrder().getId(), entityFromDb.getOrder().getId());
		assertEquals(entity.getCompany().getId(), entityFromDb.getCompany().getId());
		assertEquals(entity.getCurrency(), entityFromDb.getCurrency());
		assertEquals(entity.getRate(), entityFromDb.getRate());
		assertEquals(entity.getAmount(), entityFromDb.getAmount());
		assertEquals(entity.getNote(), entityFromDb.getNote());
		
	}

	@Test
	public void testGetAll() {
		final int intialCount = paymentService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewPayment();
		}

		final List<IPayment> allEntities = paymentService.getAll();

		for (final IPayment entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getDate());
			assertNotNull(entityFromDb.getOrder());
			assertNotNull(entityFromDb.getCompany());
			assertNotNull(entityFromDb.getCurrency());
			assertNotNull(entityFromDb.getRate());
			assertNotNull(entityFromDb.getAmount());
			assertNotNull(entityFromDb.getNote());
			
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IPayment entity = saveNewPayment();
		paymentService.delete(entity.getId());
		assertNull(paymentService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewPayment();
		paymentService.deleteAll();
		assertEquals(0, paymentService.getAll().size());
	}


}
