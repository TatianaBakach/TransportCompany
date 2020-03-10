package by.itacademy.tatabakach.transportcompany.daojdbcimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.daoapi.ITransactionCostDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.Currency;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.PaymentTermsType;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITransactionCost;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.TransactionCostFilter;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.TransactionCost;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.PreparedStatementAction;

@Repository
public class TransactionCostDaoImpl extends AbstractDaoImpl<ITransactionCost, Integer> implements ITransactionCostDao {

	@Override
	public ITransactionCost createEntity() {
		return new TransactionCost();
	}

	@Override
	public void insert(final ITransactionCost entity) {
		executeStatement(new PreparedStatementAction<ITransactionCost>(String.format(
				"insert into %s (date, currency_id, amount, rate, intermediate_currency_id, intermediate_currency_rate, payment_period, payment_terms_type_id, note) values(?,?,?,?,?,?,?,?,?)",
				getTableName()), true) {
			@Override
			public ITransactionCost doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setObject(1, entity.getDate(), Types.TIMESTAMP);
				pStmt.setInt(2, entity.getCurrency().ordinal());
				pStmt.setBigDecimal(3, entity.getAmount());
				pStmt.setBigDecimal(4, entity.getRate());
				pStmt.setInt(5, entity.getIntermediateCurrency().ordinal());
				pStmt.setBigDecimal(6, entity.getIntermediateCurrencyRate());
				pStmt.setInt(7, entity.getPaymentPeriod());
				pStmt.setInt(8, entity.getPaymentTermsType().ordinal());
				pStmt.setString(9, entity.getNote());

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
	public void update(final ITransactionCost entity) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public List<ITransactionCost> find(final TransactionCostFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final TransactionCostFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	protected ITransactionCost parseRow(final ResultSet resultSet) throws SQLException {
		final ITransactionCost entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setDate(resultSet.getTimestamp("date"));
		entity.setCurrency(Currency.values()[(resultSet.getInt("currency_id"))]);
		entity.setAmount(resultSet.getBigDecimal("amount"));
		entity.setRate(resultSet.getBigDecimal("rate"));
		entity.setIntermediateCurrency(Currency.values()[(resultSet.getInt("intermediate_currency_id"))]);
		entity.setIntermediateCurrencyRate(resultSet.getBigDecimal("intermediate_currency_rate"));
		entity.setPaymentPeriod(resultSet.getInt("payment_period"));
		entity.setPaymentTermsType(PaymentTermsType.values()[(resultSet.getInt("payment_terms_type_id"))]);
		entity.setNote(resultSet.getString("note"));

		return entity;
	}

	@Override
	protected String getTableName() {
		return "transaction_cost";
	}

}
