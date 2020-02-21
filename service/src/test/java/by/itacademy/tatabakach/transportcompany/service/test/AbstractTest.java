package by.itacademy.tatabakach.transportcompany.service.test;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICar;
import by.itacademy.tatabakach.transportcompany.service.ICarService;
import by.itacademy.tatabakach.transportcompany.service.impl.CarServiceImpl;

public abstract class AbstractTest {
	protected ICarService carService = new CarServiceImpl();

	private static final Random RANDOM = new Random();

	@BeforeEach
	public void setUpMethod() {
		carService.deleteAll(); // чистка базы

	}

	protected String getRandomPrefix() {
		return RANDOM.nextInt(99999) + "";
	}

	protected int getRandomObjectsCount() {
		return RANDOM.nextInt(9) + 1;
	}

	public Random getRANDOM() {
		return RANDOM;
	}

	protected ICar saveNewCar() {
		final ICar entity = carService.createEntity();
		entity.setModel("model-" + getRandomPrefix());
		entity.setNumber("number-" + getRandomPrefix());
		carService.save(entity);
		return entity;
	}
}