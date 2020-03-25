package by.itacademy.tatabakach.transportcompany.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IAddress;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.AddressFilter;

public class AddressServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final IAddress entity = saveNewAddress();

		final IAddress entityFromDb = addressService.getFullInfo(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertEquals(entity.getPostcode(), entityFromDb.getPostcode());
		assertEquals(entity.getLocality().getId(), entityFromDb.getLocality().getId());
		assertEquals(entity.getExactAddress(), entityFromDb.getExactAddress());
		assertEquals(entity.getNote(), entityFromDb.getNote());

	}

	@Test
	public void testGetAll() {
		AddressFilter filter = new AddressFilter();
		final long intialCount = addressService.getCount(filter);

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewAddress();
		}
		
		filter.setFetchLocality(true);

		final List<IAddress> allEntities = addressService.find(filter);

		for (final IAddress entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getPostcode());
			assertNotNull(entityFromDb.getLocality().getId());
			assertNotNull(entityFromDb.getExactAddress());
			assertNotNull(entityFromDb.getNote());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IAddress entity = saveNewAddress();
		addressService.delete(entity.getId());
		assertNull(addressService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewAddress();
		addressService.deleteAll();
		assertEquals(0, addressService.getAll().size());
	}

}
