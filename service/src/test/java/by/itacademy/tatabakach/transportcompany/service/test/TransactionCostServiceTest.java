package by.itacademy.tatabakach.transportcompany.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITransactionCost;

public class TransactionCostServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final ITransactionCost entity = saveNewTransactionCost();

		final ITransactionCost entityFromDb = transactionCostService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertEquals(entity.getDate(), entityFromDb.getDate());
		assertEquals(entity.getCurrency(), entityFromDb.getCurrency());
		assertEquals(entity.getAmount(), entityFromDb.getAmount());
		assertEquals(entity.getRate(), entityFromDb.getRate());
		assertEquals(entity.getIntermediateCurrency(), entityFromDb.getIntermediateCurrency());
		assertEquals(entity.getIntermediateCurrencyRate(), entityFromDb.getIntermediateCurrencyRate());
		assertEquals(entity.getPaymentPeriod(), entityFromDb.getPaymentPeriod());
		assertEquals(entity.getPaymentTermsType(), entityFromDb.getPaymentTermsType());
		assertEquals(entity.getNote(), entityFromDb.getNote());

	}

	@Test
	public void testGetAll() {
		final int intialCount = transactionCostService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewTransactionCost();
		}

		final List<ITransactionCost> allEntities = transactionCostService.getAll();

		for (final ITransactionCost entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getDate());
			assertNotNull(entityFromDb.getCurrency());
			assertNotNull(entityFromDb.getAmount());
			assertNotNull(entityFromDb.getRate());
			assertNotNull(entityFromDb.getIntermediateCurrency());
			assertNotNull(entityFromDb.getIntermediateCurrencyRate());
			assertNotNull(entityFromDb.getPaymentPeriod());
			assertNotNull(entityFromDb.getPaymentTermsType());
			assertNotNull(entityFromDb.getNote());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final ITransactionCost entity = saveNewTransactionCost();
		transactionCostService.delete(entity.getId());
		assertNull(transactionCostService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewTransactionCost();
		transactionCostService.deleteAll();
		assertEquals(0, transactionCostService.getAll().size());
	}

}
