package by.itacademy.tatabakach.transportcompany.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IContract;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.ContractFilter;

public class ContractServiceTest extends AbstractTest{
	
	@Test
	public void testCreate() {
		final IContract entity = saveNewContract();

		final IContract entityFromDb = contractService.getFullInfo(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertEquals(entity.getNumber(), entityFromDb.getNumber());
		assertEquals(entity.getOurCompany().getId(), entityFromDb.getOurCompany().getId());
		assertEquals(entity.getCompany().getId(), entityFromDb.getCompany().getId());
		assertEquals(entity.getDate(), entityFromDb.getDate());

	}

	@Test
	public void testGetAll() {
		ContractFilter filter = new ContractFilter();
		final long intialCount = contractService.getCount(filter);

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewContract();
		}

		final List<IContract> allEntities = contractService.find(filter);

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
