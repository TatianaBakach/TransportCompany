package by.itacademy.dzhivushko.cars.service.test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import by.itacademy.dzhivushko.cars.dao.api.entity.enums.EngineType;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IBrand;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IEngine;
import by.itacademy.dzhivushko.cars.service.IBrandService;
import by.itacademy.dzhivushko.cars.service.IEngineService;
import by.itacademy.dzhivushko.cars.service.IModelService;

@SpringJUnitConfig(locations = "classpath:service-context-test.xml")
public abstract class AbstractTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTest.class);

	@Autowired
	protected IBrandService brandService;
	@Autowired
	protected IModelService modelService;
	@Autowired
	protected IEngineService engineService;

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
				stmt.execute(getScript());
			} finally {
				stmt.close();
			}
		} finally {
			conn.close();
		}

		LOGGER.info("Database recreated in {} seconds.",
				Double.valueOf((System.currentTimeMillis() - stampBefore) / 1000));
	}

	private String getScript() {

		StringBuilder contentBuilder = new StringBuilder();

		try (Stream<String> stream = Files.lines(Paths.get("../doc/db/db.sql"), StandardCharsets.UTF_8)) {
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


	public static boolean randomBoolean() {
		return Math.random() < 0.5;
	}


	public static <T> T randomFromCollection(final Collection<T> all) {
		final int size = all.size();
		final int item = new Random().nextInt(size); // In real life, the Random
														// object should be
														// rather
														// more shared than this
		int i = 0;
		for (final T obj : all) {
			if (i == item) {
				return obj;
			}
			i = i + 1;
		}
		return null;
	}

	@SafeVarargs
	public static <T> T randomFromArray(final T... all) {
		final int size = all.length;
		final int item = new Random().nextInt(size); // In real life, the Random
														// object should be
														// rather
														// more shared than this
		int i = 0;
		for (final T obj : all) {
			if (i == item) {
				return obj;
			}
			i = i + 1;
		}
		return null;
	}

	public static Date randomDate() {
		final int year = randBetween(1900, 2010);
		final GregorianCalendar gc = new GregorianCalendar();
		gc.set(Calendar.YEAR, year);
		final int dayOfYear = randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
		gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
		return gc.getTime();
	}

	public static int randBetween(final int start, final int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}

	protected IBrand saveNewBrand() {
		final IBrand entity = brandService.createEntity();
		entity.setName("brand-" + getRandomPrefix());
		brandService.save(entity);
		return entity;
	}

	protected IEngine saveNewEngine() {
		final IEngine entity = engineService.createEntity();
		entity.setTitle("engine-" + getRandomPrefix());
		entity.setVolume(getRANDOM().nextInt(400) + 1000);

		final EngineType[] allTypes = EngineType.values();
		final int randomIndex = Math.max(0, getRANDOM().nextInt(allTypes.length) - 1);
		entity.setType(allTypes[randomIndex]);

		engineService.save(entity);
		return entity;
	}
}
