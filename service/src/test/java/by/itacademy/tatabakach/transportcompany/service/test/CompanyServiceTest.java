package by.itacademy.tatabakach.transportcompany.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;

public class CompanyServiceTest extends AbstractTest {
	
	@Test
	public void testCreate() {
		final ICompany entity = saveNewCompany();

		final ICompany entityFromDb = companyService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCompanyType());
		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getPayerRegistrationNumber(), entityFromDb.getPayerRegistrationNumber());
		assertEquals(entity.getLegalAddress().getId(), entityFromDb.getLegalAddress().getId());
		assertEquals(entity.getPostAddress().getId(), entityFromDb.getPostAddress().getId());
		assertEquals(entity.getBankData(), entityFromDb.getBankData());
		assertEquals(entity.getEMail(), entityFromDb.getEMail());
		assertEquals(entity.getPhone(), entityFromDb.getPhone());
		assertEquals(entity.getCreator().getId(), entityFromDb.getCreator().getId());

	}

	@Test
	public void testGetAll() {
		final int intialCount = companyService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewCompany();
		}

		final List<ICompany> allEntities = companyService.getAll();

		for (final ICompany entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCompanyType());
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getPayerRegistrationNumber());
			assertNotNull(entityFromDb.getLegalAddress());
			assertNotNull(entityFromDb.getPostAddress());
			assertNotNull(entityFromDb.getBankData());
			assertNotNull(entityFromDb.getEMail());
			assertNotNull(entityFromDb.getPhone());
			assertNotNull(entityFromDb.getCreator());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final ICompany entity = saveNewCompany();
		companyService.delete(entity.getId());
		assertNull(companyService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewCompany();
		companyService.deleteAll();
		assertEquals(0, companyService.getAll().size());
	}

}
