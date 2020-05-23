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
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.CorrespondenceType;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.Currency;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.Department;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.LoadingMethod;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.PaymentTermsType;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.Position;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.RewardType;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.Role;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IAddress;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICar;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICfr;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IContract;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICorrespondence;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICountry;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDriver;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ILocality;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderReward;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderRewardPercent;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IPayment;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRegion;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRouteItem;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITax;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITransactionCost;
import by.itacademy.tatabakach.transportcompany.service.IAddressService;
import by.itacademy.tatabakach.transportcompany.service.ICarService;
import by.itacademy.tatabakach.transportcompany.service.ICfrService;
import by.itacademy.tatabakach.transportcompany.service.ICompanyService;
import by.itacademy.tatabakach.transportcompany.service.IContractService;
import by.itacademy.tatabakach.transportcompany.service.ICorrespondenceService;
import by.itacademy.tatabakach.transportcompany.service.ICountryService;
import by.itacademy.tatabakach.transportcompany.service.IDriverService;
import by.itacademy.tatabakach.transportcompany.service.IEmployeeService;
import by.itacademy.tatabakach.transportcompany.service.ILocalityService;
import by.itacademy.tatabakach.transportcompany.service.IOrderRewardPercentService;
import by.itacademy.tatabakach.transportcompany.service.IOrderRewardService;
import by.itacademy.tatabakach.transportcompany.service.IOrderService;
import by.itacademy.tatabakach.transportcompany.service.IPaymentService;
import by.itacademy.tatabakach.transportcompany.service.IRegionService;
import by.itacademy.tatabakach.transportcompany.service.IRouteItemService;
import by.itacademy.tatabakach.transportcompany.service.ITaxService;
import by.itacademy.tatabakach.transportcompany.service.ITransactionCostService;

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
	protected IOrderRewardPercentService orderRewardPercentService;
	@Autowired
	protected IEmployeeService employeeService;
	@Autowired
	protected ITaxService taxService;
	@Autowired
	protected IOrderService orderService;
	@Autowired 
	protected ICorrespondenceService correspondenceService;
	@Autowired
	protected IPaymentService paymentService;
	@Autowired
	protected IRouteItemService routeItemService;
	@Autowired
	protected IOrderRewardService orderRewardService;

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
				 stmt.execute(getScript("../docs/unique_constraints.sql"));
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

	public String getRandomPrefix() {
		return RANDOM.nextInt(99999) + "";
	}

	public int getRandomObjectsCount() {
		return RANDOM.nextInt(9) + 1;
	}

	public Random getRANDOM() {
		return RANDOM;
	}

	public Date getRandomDate() {
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
	
	public boolean getRandomBoolean() {
	    Random random = new Random();
	    return random.nextBoolean();
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

	protected ILocality saveNewLocality() {
		final ILocality entity = localityService.createEntity();
		entity.setName("name-" + getRandomPrefix());
		entity.setRegion(saveNewRegion());
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
		entity.setCompanyType(getRandomFromArray(CompanyType.values()));
		entity.setName("name-" + getRandomPrefix());
		entity.setPayerRegistrationNumber("rpn-" + getRandomPrefix());
		entity.setLegalAddress(saveNewAddress());
		entity.setPostAddress(saveNewAddress());
		entity.setBankData("bankData-" + getRandomPrefix());
		entity.setMail("mail-" + getRandomPrefix());
		entity.setPhone("phone-" + getRandomPrefix());
		entity.setCreator(saveNewEmployee());

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
	
	protected IOrderRewardPercent saveNewOrderRewardPercent() {
		final IOrderRewardPercent entity = orderRewardPercentService.createEntity();
		entity.setName("name-" + getRandomPrefix());
		entity.setPercent(getRandomBigDecimal(2));
		orderRewardPercentService.save(entity);
		return entity;
	}
	
	protected IEmployee saveNewEmployee() {
		final IEmployee entity = employeeService.createEntity();
		entity.setFirstName("first_name-" + getRandomPrefix());
		entity.setMiddleName("middle_name-" + getRandomPrefix());
		entity.setLastName("last_name-" + getRandomPrefix());
		entity.setDepartment(getRandomFromArray(Department.values()));
		entity.setPosition(getRandomFromArray(Position.values()));
		entity.setMail("mail-" + getRandomPrefix());
		entity.setPhone("phone-" + getRandomPrefix());
		entity.setRole(getRandomFromArray(Role.values()));
		entity.setPassword("password-" + getRandomPrefix());
		entity.setSalary(getRandomBigDecimal(2));
		
		employeeService.save(entity);
		return entity;
	}
	
	protected ITax saveNewTax() {
		final ITax entity = taxService.createEntity();
		entity.setName("name-" + getRandomPrefix());
		entity.setRate(getRandomBigDecimal(2));
		taxService.save(entity);
		return entity;
	}
	
	protected IOrder saveNewOrder() {
		final IOrder entity = orderService.createEntity();

		entity.setNumber("#-" + getRandomPrefix());
		entity.setDate(getRandomDate());
		entity.setOurCompany(saveNewCompany());
		entity.setCustomer(saveNewCompany());
		entity.setCarrier(saveNewCompany());
		entity.setCar(saveNewCar());
		entity.setDriver(saveNewDriver());
		entity.setLoadingMethod(getRandomFromArray(LoadingMethod.values()));
		entity.setCargoType("cargoType-" + getRandomPrefix());
		entity.setCargoWeightVolume("cargoWeightVolume-" + getRandomPrefix());
		entity.setCustomerCost(saveNewTransactionCost());
		entity.setPaidCustomer(getRandomBoolean());
		entity.setCarrierCost(saveNewTransactionCost());
		entity.setPaidCarrier(getRandomBoolean());
		entity.setTax(saveNewTax());
		entity.setAdditionalConditions("additionalConditions-" + getRandomPrefix());
		entity.setCreator(saveNewEmployee());
		entity.setNote("note" + getRandomPrefix());
		
		orderService.save(entity);

		return entity;
	}
	
	protected ICorrespondence saveNewCorrespondence() {
		final ICorrespondence entity = correspondenceService.createEntity();

		entity.setCorrespondenceType(getRandomFromArray(CorrespondenceType.values()));
		entity.setOrder(saveNewOrder());
		entity.setCompany(saveNewCompany());
		entity.setDate(getRandomDate());
		entity.setContent("content-" + getRandomPrefix());
		entity.setNote("note" + getRandomPrefix());
		
		correspondenceService.save(entity);

		return entity;
	}
	
	protected IPayment saveNewPayment() {
		final IPayment entity = paymentService.createEntity();

		entity.setDate(getRandomDate());
		entity.setOrder(saveNewOrder());
		entity.setCompany(saveNewCompany());
		entity.setCurrency(getRandomFromArray(Currency.values()));
		entity.setRate(getRandomBigDecimal(4));
		entity.setAmount(getRandomBigDecimal(2));
		entity.setNote("note" + getRandomPrefix());
		
		paymentService.save(entity);

		return entity;
	}
	
	protected IRouteItem saveNewRouteItem() {
		final IRouteItem entity = routeItemService.createEntity();
		entity.setOrder(saveNewOrder());
		entity.setAddress(saveNewAddress());
		entity.setDate(getRandomDate());
		entity.setCargoWeight("cargoWeight-" + getRandomPrefix());
		entity.setCargoVolume("cargoVolume-" + getRandomPrefix());
		entity.setCustom(saveNewAddress());
		entity.setContactPerson("contactPerson-" + getRandomPrefix());
		entity.setContactPhone("contactPhone-" + getRandomPrefix());
		entity.setNote("note" + getRandomPrefix());

		routeItemService.save(entity);
		
		return entity;
	}
	
	protected IOrderReward saveNewOrderReward() {
		final IOrderReward entity = orderRewardService.createEntity();
		entity.setOrder(saveNewOrder());
		entity.setEmployee(saveNewEmployee());
		entity.setRewardType(getRandomFromArray(RewardType.values()));
		entity.setOrderRewardPercent(saveNewOrderRewardPercent());
		entity.setAdditionalExpenses(getRandomBigDecimal(2));
		entity.setAmount(getRandomBigDecimal(2));
		entity.setPaymentDate(getRandomDate());
		entity.setRewardIssued(getRandomBoolean());
		entity.setNote("note" + getRandomPrefix());

		orderRewardService.save(entity);
		
		return entity;
	}
}