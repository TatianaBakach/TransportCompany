package by.itacademy.tatabakach.transportcompany.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDepartment;

public class DepartmentServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final IDepartment entity = saveNewDepartment();

		final IDepartment entityFromDb = departmentService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertEquals(entity.getName(), entityFromDb.getName());

	}

	@Test
	public void testGetAll() {
		final int intialCount = departmentService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewDepartment();
		}

		final List<IDepartment> allEntities = departmentService.getAll();

		for (final IDepartment entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getName());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IDepartment entity = saveNewDepartment();
		departmentService.delete(entity.getId());
		assertNull(departmentService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewDepartment();
		departmentService.deleteAll();
		assertEquals(0, departmentService.getAll().size());
	}

}
