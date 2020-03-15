package by.itacademy.tatabakach.transportcompany.service.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Date;
import java.util.Random;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.CompanyType;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.Currency;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.LoadingMethod;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.PaymentTermsType;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IAddress;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICar;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICfr;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IContract;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICountry;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDepartment;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDistrict;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDriver;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ILocality;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderRewardPercent;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IPosition;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRegion;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITransactionCost;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IVat;
import by.itacademy.tatabakach.transportcompany.service.IAddressService;
import by.itacademy.tatabakach.transportcompany.service.ICarService;
import by.itacademy.tatabakach.transportcompany.service.ICfrService;
import by.itacademy.tatabakach.transportcompany.service.ICompanyService;
import by.itacademy.tatabakach.transportcompany.service.IContractService;
import by.itacademy.tatabakach.transportcompany.service.ICountryService;
import by.itacademy.tatabakach.transportcompany.service.IDepartmentService;
import by.itacademy.tatabakach.transportcompany.service.IDistrictService;
import by.itacademy.tatabakach.transportcompany.service.IDriverService;
import by.itacademy.tatabakach.transportcompany.service.IEmployeeService;
import by.itacademy.tatabakach.transportcompany.service.ILocalityService;
import by.itacademy.tatabakach.transportcompany.service.IOrderRewardPercentService;
import by.itacademy.tatabakach.transportcompany.service.IOrderService;
import by.itacademy.tatabakach.transportcompany.service.IPositionService;
import by.itacademy.tatabakach.transportcompany.service.IRegionService;
import by.itacademy.tatabakach.transportcompany.service.ITransactionCostService;
import by.itacademy.tatabakach.transportcompany.service.IVatService;

@SpringJUnitConfig(locations = "classpath:service-context-test.xml")
public abstract class AbstractTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTest.class);

	@Autowired
	protected ICarService carService;
	@Autowired
	protected IDriverService driverService;
	@Autowired
	protected ITransactionCostService transactionCostService;
	@Autowired
	protected ICountryService countryService;
	@Autowired
	protected IRegionService regionService;
	@Autowired
	protected IDistrictService districtService;
	@Autowired
	protected ILocalityService localityService;
	@Autowired
	protected IAddressService addressService;
	@Autowired
	protected ICompanyService companyService;
	@Autowired
	protected IContractService contractService;
	@Autowired
	protected ICfrService cfrService;
	@Autowired
	protected IDepartmentService departmentService;
	@Autowired
	protected IPositionService positionService;
	@Autowired
	protected IOrderRewardPercentService orderRewardPercentService;
	@Autowired
	protected IEmployeeService employeeService;
	@Autowired
	protected IVatService vatService;
	@Autowired
	protected IOrderService orderService;

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
				 stmt.execute(getScript(".../docs/unique constraints.sql"));
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

		try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
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

	protected Date getRandomDate() {
		return new Date();
	}

	

	public static BigDecimal getRandomBigDecimal(int scale) {
		BigDecimal max = new BigDecimal(99);
		BigDecimal randFromDouble = new BigDecimal(Math.random());
		BigDecimal actualRandomDec = randFromDouble.multiply(max);
		actualRandomDec = actualRandomDec.setScale(scale, BigDecimal.ROUND_DOWN);
		return actualRandomDec;
	}

	public static <T> T getRandomFromCollection(final Collection<T> all) {
		final int size = all.size();
		final int item = new Random().nextInt(size); // In real life, the Random object should be rather more shared
														// than this
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
	public static <T> T getRandomFromArray(final T... all) {
		final int size = all.length;
		final int item = new Random().nextInt(size); // In real life, the Random object should be rather more shared
														// than this
		int i = 0;
		for (final T obj : all) {
			if (i == item) {
				return obj;
			}
			i = i + 1;
		}
		return null;
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
		entity.setFirstName("first_name-" + getRandomPrefix());
		entity.setMiddleName("middle_name-" + getRandomPrefix());
		entity.setLastName("last_name-" + getRandomPrefix());
		entity.setPassportData("passport_data-" + getRandomPrefix());
		entity.setPhone("phone-" + getRandomPrefix());
		driverService.save(entity);
		return entity;
	}

	protected ITransactionCost saveNewTransactionCost() {
		final ITransactionCost entity = transactionCostService.createEntity();

		//final Currency[] allCurrencyTypes = Currency.values();
		//final int randomCurrencyIndex = Math.max(0, getRANDOM().nextInt(allCurrencyTypes.length) - 1);

		//Currency randomCurrency = getRandomFromArray(Currency.values());

		//final PaymentTermsType[] allPaymentTermsTypes = PaymentTermsType.values();
		//final int randomPaymentTermsIndex = Math.max(0, getRANDOM().nextInt(allPaymentTermsTypes.length) - 1);

		entity.setDate(getRandomDate());
		entity.setCurrency(getRandomFromArray(Currency.values()));
		entity.setAmount(getRandomBigDecimal(2));
		entity.setRate(getRandomBigDecimal(4));
		entity.setIntermediateCurrency(getRandomFromArray(Currency.values()));
		entity.setIntermediateCurrencyRate(getRandomBigDecimal(4));
		entity.setPaymentPeriod(getRANDOM().nextInt(30));
		entity.setPaymentTermsType(getRandomFromArray(PaymentTermsType.values()));
		entity.setNote("note" + getRandomPrefix());

		transactionCostService.save(entity);

		return entity;
	}

	protected ICountry saveNewCountry() {
		final ICountry entity = countryService.createEntity();
		entity.setName("name-" + getRandomPrefix());
		countryService.save(entity);
		return entity;
	}

	protected IRegion saveNewRegion() {
		final IRegion entity = regionService.createEntity();
		entity.setName("name-" + getRandomPrefix());
		entity.setCountry(saveNewCountry());
		regionService.save(entity);
		return entity;
	}

	protected IDistrict saveNewDistrict() {
		final IDistrict entity = districtService.createEntity();
		entity.setName("name-" + getRandomPrefix());
		entity.setRegion(saveNewRegion());
		districtService.save(entity);
		return entity;
	}

	protected ILocality saveNewLocality() {
		final ILocality entity = localityService.createEntity();
		entity.setName("name-" + getRandomPrefix());
		entity.setDistrict(saveNewDistrict());
		localityService.save(entity);
		return entity;
	}

	protected IAddress saveNewAddress() {
		final IAddress entity = addressService.createEntity();
		entity.setPostcode("#" + getRandomPrefix());
		entity.setLocality(saveNewLocality());
		entity.setExactAddress("exactAddress-" + getRandomPrefix());
		entity.setNote("note-" + getRandomPrefix());
		addressService.save(entity);
		return entity;
	}

	protected ICompany saveNewCompany() {
		final ICompany entity = companyService.createEntity();
		entity.setName("name-" + getRandomPrefix());
		entity.setPayerRegistrationNumber("rpn-" + getRandomPrefix());
		entity.setLegalAddress(saveNewAddress());
		entity.setPostAddress(saveNewAddress());
		entity.setBankData("bankData-" + getRandomPrefix());
		entity.setEMail("eMail-" + getRandomPrefix());
		entity.setPhone("phone-" + getRandomPrefix());

		final CompanyType[] allTypes = CompanyType.values();
		final int randomIndex = Math.max(0, getRANDOM().nextInt(allTypes.length) - 1);
		entity.setCompanyType(allTypes[randomIndex]);

		companyService.save(entity);
		return entity;
	}

	protected IContract saveNewContract() {
		final IContract entity = contractService.createEntity();
		entity.setNumber("number-" + getRandomPrefix());
		entity.setOurCompany(saveNewCompany());
		entity.setCompany(saveNewCompany());
		entity.setDate(getRandomDate());

		contractService.save(entity);
		return entity;
	}
	
	protected ICfr saveNewCfr() {
		final ICfr entity = cfrService.createEntity();
		entity.setCompany(saveNewCompany());
		entity.setYear(getRandomObjectsCount());

		cfrService.save(entity);
		return entity;
	}
	
	protected IDepartment saveNewDepartment() {
		final IDepartment entity = departmentService.createEntity();
		entity.setName("name-" + getRandomPrefix());
		departmentService.save(entity);
		return entity;
	}
	
	protected IPosition saveNewPosition() {
		final IPosition entity = positionService.createEntity();
		entity.setName("name-" + getRandomPrefix());
		positionService.save(entity);
		return entity;
	}
	
	protected IOrderRewardPercent saveNewOrderRewardPercent() {
		final IOrderRewardPercent entity = orderRewardPercentService.createEntity();
		entity.setPercent(getRandomBigDecimal(2));
		orderRewardPercentService.save(entity);
		return entity;
	}
	
	protected IEmployee saveNewEmployee() {
		final IEmployee entity = employeeService.createEntity();
		entity.setFirstName("first_name-" + getRandomPrefix());
		entity.setMiddleName("middle_name-" + getRandomPrefix());
		entity.setLastName("last_name-" + getRandomPrefix());
		entity.setDepartment(saveNewDepartment());
		entity.setPosition(saveNewPosition());
		entity.setEMail("eMail-" + getRandomPrefix());
		entity.setPhone("phone-" + getRandomPrefix());
		entity.setLogin("login-" + getRandomPrefix());
		entity.setPassword("password-" + getRandomPrefix());
		entity.setSalary(getRandomBigDecimal(2));
		
		employeeService.save(entity);
		return entity;
	}
	
	protected IVat saveNewVat() {
		final IVat entity = vatService.createEntity();
		entity.setName("name-" + getRandomPrefix());
		entity.setRate(getRandomBigDecimal(2));
		vatService.save(entity);
		return entity;
	}
	
	protected IOrder saveNewOrder() {
		final IOrder entity = orderService.createEntity();

		entity.setNumber("number-" + getRandomPrefix());
		entity.setOurCompany(saveNewCompany());
		entity.setCustomer(saveNewCompany());
		entity.setCarrier(saveNewCompany());
		entity.setCar(saveNewCar());
		entity.setDriver(saveNewDriver());
		entity.setLoadingMethod(getRandomFromArray(LoadingMethod.values()));
		entity.setCargoType("cargoType-" + getRandomPrefix());
		entity.setCargoWeightVolume("cargoWeightVolume-" + getRandomPrefix());
		entity.setCustomerCost(saveNewTransactionCost());
		entity.setPaidCustomer(false);
		entity.setCarrierCost(saveNewTransactionCost());
		entity.setPaidCarrier(false);
		entity.setVat(saveNewVat());
		entity.setAdditionalConditions("additionalConditions-" + getRandomPrefix());
		
		orderService.save(entity);

		return entity;
	}
}