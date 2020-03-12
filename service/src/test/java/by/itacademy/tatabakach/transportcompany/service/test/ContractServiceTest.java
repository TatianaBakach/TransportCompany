package by.itacademy.tatabakach.transportcompany.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IContract;

public class ContractServiceTest extends AbstractTest{
	
	@Test
	public void testCreate() {
		final IContract entity = saveNewContract();

		final IContract entityFromDb = contractService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertEquals(entity.getNumber(), entityFromDb.getNumber());
		assertEquals(entity.getOurCompany().getId(), entityFromDb.getOurCompany().getId());
		assertEquals(entity.getCompany().getId(), entityFromDb.getCompany().getId());
		assertEquals(entity.getDate(), entityFromDb.getDate());

	}

	@Test
	public void testGetAll() {
		final int intialCount = contractService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewContract();
		}

		final List<IContract> allEntities = contractService.getAll();

		for (final IContract entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getNumber());
			assertNotNull(entityFromDb.getOurCompany());
			assertNotNull(entityFromDb.getCompany());
			assertNotNull(entityFromDb.getDate());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IContract entity = saveNewContract();
		contractService.delete(entity.getId());
		assertNull(contractService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewContract();
		contractService.deleteAll();
		assertEquals(0, contractService.getAll().size());
	}

}
