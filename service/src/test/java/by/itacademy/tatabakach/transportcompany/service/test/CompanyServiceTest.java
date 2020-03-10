package by.itacademy.tatabakach.transportcompany.service.test;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;

public class CompanyServiceTest extends AbstractTest {

	
	public void testUnique() {
		ICompany entity = companyService.createEntity();
		
		//set fields
		
		
		companyService.save(entity);
		
		try {
			// save entity with the same fields
			entity.setId(null);
			companyService.save(entity);
			
			fail("object can't be saved with the same unique key");
			
		}catch (Exception e) {
			// ok, constraint exists
		}

	}
	
	
	@Test
	public void testCreate() {
		final ICompany entity = saveNewCompany();

		final ICompany entityFromDb = companyService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCompanyType());
		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getPayerRegistrationNumber(), entityFromDb.getPayerRegistrationNumber());
		assertEquals(entity.getAddress().getId(), entityFromDb.getAddress().getId());
		assertEquals(entity.getBankData(), entityFromDb.getBankData());
		assertEquals(entity.getEMail(), entityFromDb.getEMail());
		assertEquals(entity.getPhone(), entityFromDb.getPhone());

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
			assertNotNull(entityFromDb.getAddress());
			assertNotNull(entityFromDb.getBankData());
			assertNotNull(entityFromDb.getEMail());
			assertNotNull(entityFromDb.getPhone());
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
