package by.itacademy.tatabakach.transportcompany.daojdbcimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.daoapi.ICompanyDao;
import by.itacademy.tatabakach.transportcompany.daoapi.IOrderDao;
import by.itacademy.tatabakach.transportcompany.daoapi.IPaymentDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.Currency;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IPayment;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.PaymentFilter;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Company;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Order;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Payment;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.PreparedStatementAction;

@Repository
public class PaymentDaoImpl extends AbstractDaoImpl<IPayment, Integer> implements IPaymentDao{
	
	@Autowired
	private IOrderDao orderDao;
	
	@Autowired
	private ICompanyDao companyDao;
	
	@Override
	public IPayment createEntity() {
		return new Payment();
	}

	@Override
	public void insert(final IPayment entity) {
		executeStatement(new PreparedStatementAction<IPayment>(String.format(
				"insert into %s (date, order_id, company_id, currency_id, rate, amount) values(?,?,?,?,?,?)",
				getTableName()), true) {
			@Override
			public IPayment doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setObject(1, entity.getDate(), Types.TIMESTAMP);
				pStmt.setInt(2, entity.getOrder().getId());
				pStmt.setInt(3, entity.getCompany().getId());
				pStmt.setInt(4, entity.getCurrency().ordinal());
				pStmt.setBigDecimal(5, entity.getRate());
				pStmt.setBigDecimal(6, entity.getAmount());
				
				pStmt.executeUpdate();

				final ResultSet rs = pStmt.getGeneratedKeys();
				rs.next();
				final int id = rs.getInt("id");

				rs.close();

				entity.setId(id);
				return entity;
			}
		});

	}

	@Override
	public void update(final IPayment entity) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public List<IPayment> find(final PaymentFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final PaymentFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	protected IPayment parseRow(final ResultSet resultSet) throws SQLException {
		final IPayment entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setDate(resultSet.getTimestamp("date"));
		
		final Integer orderId = (Integer) resultSet.getObject("order_id");
		if (orderId != null) {
			final Order order = new Order();
			order.setId(orderId);
			entity.setOrder(order);
		}
		
		final Integer companyId = (Integer) resultSet.getObject("company_id");
		if (companyId != null) {
			final Company company = new Company();
			company.setId(companyId);
			entity.setCompany(company);
		}
		
		entity.setCurrency(Currency.values()[(resultSet.getInt("currency_id"))]);
		entity.setRate(resultSet.getBigDecimal("rate"));
		entity.setAmount(resultSet.getBigDecimal("amount"));
		
		return entity;
	}
	
	@Override
	public IPayment getFullInfo(final Integer id) {
		final IPayment payment = get(id);

		if (payment.getOrder() != null) {
			payment.setOrder(orderDao.get(payment.getOrder().getId()));
		}

		if (payment.getCompany() != null) {
			payment.setCompany(companyDao.get(payment.getCompany().getId()));
		}

		return payment;
	}

	@Override
	protected String getTableName() {
		return "payment";
	}

}
