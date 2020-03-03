package by.itacademy.tatabakach.transportcompany.service.test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

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

@SpringJUnitConfig(locations = "classpath:service-context-test.xml")
public abstract class AbstractTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTest.class);
	
	@Autowired
	protected ICarService carService;
	@Autowired
	protected IDriverService driverService;
	@Autowired
	protected ICompanyService companyService;

	private static final Random RANDOM = new Random();

	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.user}")
	private String user;
	@Value("${jdbc.password}")
	private String password;

	@BeforeEach
	public final void recreateTestDB() throws SQLException, IOException {
		final long stampBefore = System.currentTimeMillis();

		final Connection conn = DriverManager.getConnection(url, user, password);

		try {
			final Statement stmt = conn.createStatement();
			try {
				stmt.execute("DROP SCHEMA IF EXISTS \"public\" CASCADE;");
				stmt.execute("CREATE SCHEMA \"public\";");
				stmt.execute(getScript("../docs/грузоперевозки_postgres_create.sql"));
			} finally {
				stmt.close();
			}
		} finally {
			conn.close();
		}

		LOGGER.info("Database recreated in {} seconds.",
				Double.valueOf((System.currentTimeMillis() - stampBefore) / 1000));
	}

	private String getScript(String filePath) {

		StringBuilder contentBuilder = new StringBuilder();

		try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) { // поменять путь 
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return contentBuilder.toString();
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
		entity.setPayerRegistrationNumber("#" + getRandomPrefix());
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