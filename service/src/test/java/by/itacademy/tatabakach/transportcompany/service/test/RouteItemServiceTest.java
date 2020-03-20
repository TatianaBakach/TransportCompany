package by.itacademy.tatabakach.transportcompany.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRouteItem;

public class RouteItemServiceTest extends AbstractTest {
	
	@Test
	public void testCreate() {
		final IRouteItem entity = saveNewRouteItem();

		final IRouteItem entityFromDb = routeItemService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertEquals(entity.getOrder().getId(), entityFromDb.getOrder().getId());
		assertEquals(entity.getAddress().getId(), entityFromDb.getAddress().getId());
		assertEquals(entity.getDate(), entityFromDb.getDate());
		assertEquals(entity.getCargoWeight(), entityFromDb.getCargoWeight());
		assertEquals(entity.getCargoVolume(), entityFromDb.getCargoVolume());
		assertEquals(entity.getCustom().getId(), entityFromDb.getCustom().getId());
		assertEquals(entity.getContactPerson(), entityFromDb.getContactPerson());
		assertEquals(entity.getContactPhone(), entityFromDb.getContactPhone());

	}

	@Test
	public void testGetAll() {
		final int intialCount = routeItemService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewRouteItem();
		}

		final List<IRouteItem> allEntities = routeItemService.getAll();

		for (final IRouteItem entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getOrder());
			assertNotNull(entityFromDb.getAddress());
			assertNotNull(entityFromDb.getDate());
			assertNotNull(entityFromDb.getCargoWeight());
			assertNotNull(entityFromDb.getCargoVolume());
			assertNotNull(entityFromDb.getCustom());
			assertNotNull(entityFromDb.getContactPerson());
			assertNotNull(entityFromDb.getContactPhone());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IRouteItem entity = saveNewRouteItem();
		routeItemService.delete(entity.getId());
		assertNull(routeItemService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewRouteItem();
		routeItemService.deleteAll();
		assertEquals(0, routeItemService.getAll().size());
	}

}
