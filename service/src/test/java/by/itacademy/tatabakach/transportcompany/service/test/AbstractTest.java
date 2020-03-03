package by.itacademy.tatabakach.transportcompany.service.test;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.CompanyType;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICar;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDriver;
import by.itacademy.tatabakach.transportcompany.service.ICarService;
import by.itacademy.tatabakach.transportcompany.service.ICompanyService;
import by.itacademy.tatabakach.transportcompany.service.IDriverService;
import by.itacademy.tatabakach.transportcompany.service.impl.CarServiceImpl;
import by.itacademy.tatabakach.transportcompany.service.impl.CompanyServiceImpl;
import by.itacademy.tatabakach.transportcompany.service.impl.DriverServiceImpl;

public abstract class AbstractTest {
	protected ICarService carService = new CarServiceImpl();
	protected IDriverService driverService = new DriverServiceImpl();
	protected ICompanyService companyService = new CompanyServiceImpl();

	private static final Random RANDOM = new Random();

	@BeforeEach
	public void setUpMethod() {
		carService.deleteAll(); // чистка базы
		companyService.deleteAll();

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
	
	protected IDriver saveNewDriver() {
		final IDriver entity = driverService.createEntity();
		entity.setFirstName("firstName-" + getRandomPrefix());
		entity.setMiddleName("middleName-" + getRandomPrefix());
		entity.setLastName("lastName-" + getRandomPrefix());
		entity.setPassportData("passportData-" + getRandomPrefix());
		entity.setPhone("phone-" + getRandomPrefix());
		driverService.save(entity);
		return entity;
	}

	protected ICompany saveNewCompany() {
		final ICompany entity = companyService.createEntity();
		entity.setName("name-" + getRandomPrefix());
		entity.setPayerRegistrationNumber("payerRegistrationNumber-" + getRandomPrefix());
		entity.setLegalAddress("legalAddress-" + getRandomPrefix());
		entity.setPostAddress("postAddress-" + getRandomPrefix());
		entity.setBankData("bankData-" + getRandomPrefix());
		entity.setEMail("eMail-" + getRandomPrefix());
		entity.setPhone("phone" + getRandomPrefix());

		final CompanyType[] allTypes = CompanyType.values();
		final int randomIndex = Math.max(0, getRANDOM().nextInt(allTypes.length) - 1);
		entity.setCompanyType(allTypes[randomIndex]);

		companyService.save(entity);
		return entity;
	}
}