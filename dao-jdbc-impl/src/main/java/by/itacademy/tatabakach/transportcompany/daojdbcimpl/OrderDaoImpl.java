package by.itacademy.tatabakach.transportcompany.daojdbcimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.daoapi.ICarDao;
import by.itacademy.tatabakach.transportcompany.daoapi.ICompanyDao;
import by.itacademy.tatabakach.transportcompany.daoapi.IDriverDao;
import by.itacademy.tatabakach.transportcompany.daoapi.IEmployeeDao;
import by.itacademy.tatabakach.transportcompany.daoapi.IOrderDao;
import by.itacademy.tatabakach.transportcompany.daoapi.ITransactionCostDao;
import by.itacademy.tatabakach.transportcompany.daoapi.IVatDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.LoadingMethod;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.OrderFilter;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Car;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Company;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Driver;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Order;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.TransactionCost;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Vat;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.SQLExecutionException;

@Repository
public class OrderDaoImpl extends AbstractDaoImpl<IOrder, Integer> implements IOrderDao {

	@Autowired
	private ICompanyDao companyDao;
	
	@Autowired
	private ICarDao carDao;
	
	@Autowired
	private IDriverDao driverDao;
	
	@Autowired
	private ITransactionCostDao transactionCostDao;
	
	@Autowired
	private IVatDao vatDao;
	
	@Autowired
	private IEmployeeDao employeeDao;

	@Override
	public IOrder createEntity() {
		return new Order();
	}

	@Override
	public void insert(final IOrder entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String
						.format("insert into %s (number, our_company_id, customer_id, carrier_id, car_id, driver_id, "
								+ "loading_method_id, cargo_type, cargo_weight_volume, customer_cost_id, paid_customer, "
								+ "carrier_cost_id, paid_carrier, vat_id, additional_conditions) "
								+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", getTableName()),
						Statement.RETURN_GENERATED_KEYS)) {
			c.setAutoCommit(false);
			try {
				pStmt.setString(1, entity.getNumber());
				pStmt.setInt(2, entity.getOurCompany().getId());
				pStmt.setInt(3, entity.getCustomer().getId());
				pStmt.setInt(4, entity.getCarrier().getId());
				pStmt.setInt(5, entity.getCar().getId());
				pStmt.setInt(6, entity.getDriver().getId());
				pStmt.setInt(7, entity.getLoadingMethod().ordinal());
				pStmt.setString(8, entity.getCargoType());
				pStmt.setString(9, entity.getCargoWeightVolume());
				pStmt.setInt(10, entity.getCustomerCost().getId());
				pStmt.setBoolean(11, entity.getPaidCustomer());
				pStmt.setInt(12, entity.getCarrierCost().getId());
				pStmt.setBoolean(13, entity.getPaidCarrier());
				pStmt.setInt(14, entity.getVat().getId());
				pStmt.setString(15, entity.getAdditionalConditions());
				
				

				pStmt.executeUpdate();

				final ResultSet rs = pStmt.getGeneratedKeys();
				rs.next();
				final int id = rs.getInt("id");

				rs.close();
				entity.setId(id);
				// the same should be done in 'update' DAO method
				updateEmployees(entity, c);
				c.commit();
			} catch (final Exception e) {
				c.rollback();
				throw new RuntimeException(e);
			}

		} catch (final SQLException e) {
			throw new SQLExecutionException(e);
		}
	}
	
	@Override
	public void update(final IOrder entity) {
		throw new RuntimeException("will be implemented in ORM layer");
	}
	
	@Override
	protected IOrder parseRow(final ResultSet resultSet, final Set<String> columns) throws SQLException {
		final IOrder entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setNumber(resultSet.getString("number"));
		
		final Integer ourCompanyId = (Integer) resultSet.getObject("our_company_id");
		if (ourCompanyId != null) {
			final Company ourCompany = new Company();
			ourCompany.setId(ourCompanyId);
			entity.setOurCompany(ourCompany);
		}
		
		final Integer customerId = (Integer) resultSet.getObject("customer_id");
		if(customerId !=null) {
			final Company customer = new Company();
			customer.setId(customerId);
			entity.setCustomer(customer);
		}
		
		final Integer carrierId = (Integer) resultSet.getObject("carrier_id");
		if(carrierId !=null) {
			final Company carrier = new Company();
			carrier.setId(carrierId);
			entity.setCarrier(carrier);
		}
		
		final Integer carId = (Integer) resultSet.getObject("car_id");
		if(carId !=null) {
			final Car car = new Car();
			car.setId(carId);
			entity.setCar(car);
		}
		
		final Integer driverId = (Integer) resultSet.getObject("driver_id");
		if(driverId !=null) {
			final Driver driver = new Driver();
			driver.setId(driverId);
			entity.setDriver(driver);
		}
		
		entity.setLoadingMethod(LoadingMethod.values()[(resultSet.getInt("loading_method_id"))]);
		entity.setCargoType(resultSet.getString("cargo_type"));
		entity.setCargoWeightVolume(resultSet.getString("cargo_weight_volume"));
		
		final Integer customerCostId = (Integer) resultSet.getObject("customer_cost_id");
		if(customerCostId !=null) {
			final TransactionCost customerCost = new TransactionCost();
			customerCost.setId(customerCostId);
			entity.setCustomerCost(customerCost);
		}
		
		entity.setPaidCustomer(resultSet.getBoolean("paid_customer"));
		
		final Integer carrierCostId = (Integer) resultSet.getObject("carrier_cost_id");
		if(carrierCostId !=null) {
			final TransactionCost carrierCost = new TransactionCost();
			carrierCost.setId(carrierCostId);
			entity.setCarrierCost(carrierCost);
		}
		
		entity.setPaidCarrier(resultSet.getBoolean("paid_carrier"));
		
		final Integer vatId = (Integer) resultSet.getObject("vat_id");
		if(vatId !=null) {
			final Vat vat = new Vat();
			vat.setId(vatId);
			entity.setVat(vat);
		}
		
		entity.setAdditionalConditions(resultSet.getString("additional_conditions"));
				

		return entity;
	}
	
	@Override
	public void deleteAll() {
		try (Connection c = getConnection();
				PreparedStatement deleteEmployeeRefsStmt = c.prepareStatement("delete from order_2_employee");
				PreparedStatement deleteAllStmt = c.prepareStatement("delete from " + getTableName());) {
			c.setAutoCommit(false);
			try {
				deleteEmployeeRefsStmt.executeUpdate();
				deleteAllStmt.executeUpdate();

				deleteEmployeeRefsStmt.close();
				deleteAllStmt.close();

				c.commit();
			} catch (final Exception e) {
				c.rollback();
				throw new RuntimeException(e);
			}

		} catch (final SQLException e) {
			throw new SQLExecutionException(e);
		}

	}


	@Override
	public List<IOrder> find(final OrderFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final OrderFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public IOrder getFullInfo(final Integer id) {
		final IOrder order = get(id);
		final Set<IEmployee> employees = employeeDao.getByOrder(id);
		order.setEmployees(employees);;
		
		if (order.getOurCompany() != null) {
			order.setOurCompany(companyDao.get(order.getOurCompany().getId()));
		}
		
		if(order.getCustomer() !=null) {
			order.setCustomer(companyDao.get(order.getCustomer().getId()));
		}
		
		if(order.getCarrier() !=null) {
			order.setCarrier(companyDao.get(order.getCarrier().getId()));
		}
		
		if(order.getCar() !=null) {
			order.setCar(carDao.get(order.getCar().getId()));
		}
		
		if(order.getDriver() !=null) {
			order.setDriver(driverDao.get(order.getDriver().getId()));
		}
		
		if(order.getCustomerCost() !=null) {
			order.setCustomerCost(transactionCostDao.get(order.getCustomerCost().getId()));
		}
		
		if(order.getCarrierCost() !=null) {
			order.setCarrierCost(transactionCostDao.get(order.getCarrierCost().getId()));
		}
		
		if(order.getVat() !=null) {
			order.setVat(vatDao.get(order.getVat().getId()));
		}
		
		return order;
	}
	
	@Override
	protected String getTableName() {
		return "order";
	}
	
	private void updateEmployees(final IOrder entity, final Connection c) throws SQLException {
		// clear all existing records related to the current order
		final PreparedStatement deleteStmt = c.prepareStatement("delete from order_2_employee where order_id=?");
		deleteStmt.setInt(1, entity.getId());
		deleteStmt.executeUpdate();
		deleteStmt.close();

		if (entity.getEmployees().isEmpty()) {
			return;
		}

		// insert actual list of records using 'batch'
		final PreparedStatement pStmt = c
				.prepareStatement("insert into order_2_employee (order_id, employee_id) values(?,?)");

		for (final IEmployee e : entity.getEmployees()) {
			pStmt.setInt(1, entity.getId());
			pStmt.setInt(2, e.getId());
			pStmt.addBatch();
		}
		pStmt.executeBatch();
		pStmt.close();
	}

}
