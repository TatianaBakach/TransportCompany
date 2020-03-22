package by.itacademy.tatabakach.transportcompany.daojdbcimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.daoapi.IEmployeeDao;
import by.itacademy.tatabakach.transportcompany.daoapi.IOrderDao;
import by.itacademy.tatabakach.transportcompany.daoapi.IOrderRewardDao;
import by.itacademy.tatabakach.transportcompany.daoapi.IOrderRewardPercentDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.RewardType;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderReward;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.OrderRewardFilter;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Employee;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Order;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.OrderReward;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.OrderRewardPercent;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.PreparedStatementAction;

@Repository
public class OrderRewardDaoImpl extends AbstractDaoImpl<IOrderReward, Integer> implements IOrderRewardDao {

	@Autowired
	private IOrderDao orderDao;

	@Autowired
	private IEmployeeDao employeeDao;

	@Autowired
	private IOrderRewardPercentDao orderRewardPercentDao;
	
	@Override
	public IOrderReward createEntity() {
		return new OrderReward();
	}

	@Override
	public void insert(final IOrderReward entity) {
		executeStatement(new PreparedStatementAction<IOrderReward>(String.format(
				"insert into %s (order_id, employee_id, reward_type_id, order_reward_percent_id, additional_expenses, amount, payment_date, reward_issued, note) values(?,?,?,?,?,?,?,?,?)",
				getTableName()), true) {
			@Override
			public IOrderReward doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getOrder().getId());
				pStmt.setInt(2, entity.getEmployee().getId());
				pStmt.setInt(3, entity.getRewardType().ordinal());
				pStmt.setInt(4, entity.getOrderRewardPercent().getId());
				pStmt.setBigDecimal(5, entity.getAdditionalExpenses());
				pStmt.setBigDecimal(6, entity.getAmount());
				pStmt.setObject(7, entity.getPaymentDate(), Types.TIMESTAMP);
				pStmt.setBoolean(8, entity.getRewardIssued());
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
	public void update(final IOrderReward entity) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public List<IOrderReward> find(final OrderRewardFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final OrderRewardFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	protected IOrderReward parseRow(final ResultSet resultSet) throws SQLException {
		final IOrderReward entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		
		final Integer orderId = (Integer) resultSet.getObject("order_id");
		if (orderId != null) {
			final Order order = new Order();
			order.setId(orderId);
			entity.setOrder(order);
		}

		final Integer employeeId = (Integer) resultSet.getObject("employee_id");
		if (employeeId != null) {
			final Employee employee = new Employee();
			employee.setId(employeeId);
			entity.setEmployee(employee);
		}
		
		entity.setRewardType(RewardType.values()[(resultSet.getInt(" reward_type_id"))]);
		
		final Integer orderRewardPercentId = (Integer) resultSet.getObject("order_reward_percent_id");
		if (orderRewardPercentId != null) {
			final OrderRewardPercent orderRewardPercent = new OrderRewardPercent();
			orderRewardPercent.setId(orderRewardPercentId);
			entity.setOrderRewardPercent(orderRewardPercent);
		}
		
		entity.setAdditionalExpenses(resultSet.getBigDecimal("additional_expenses"));
		entity.setAmount(resultSet.getBigDecimal("amount"));
		entity.setPaymentDate(resultSet.getTimestamp("payment_date"));
		entity.setRewardIssued(resultSet.getBoolean("reward_issued"));
		entity.setNote(resultSet.getString("note"));

		return entity;
	}

	@Override
	public IOrderReward getFullInfo(final Integer id) {
		final IOrderReward orderReward = get(id);

		if (orderReward.getOrder() != null) {
			orderReward.setOrder(orderDao.get(orderReward.getOrder().getId()));
		}

		if (orderReward.getEmployee() != null) {
			orderReward.setEmployee(employeeDao.get(orderReward.getEmployee().getId()));
		}
		
		if (orderReward.getOrderRewardPercent() != null) {
			orderReward.setOrderRewardPercent(orderRewardPercentDao.get(orderReward.getOrderRewardPercent().getId()));
		}

		return orderReward;
	}

	@Override
	protected String getTableName() {
		return "order_reward";
	}

}
