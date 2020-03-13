package by.itacademy.tatabakach.transportcompany.daojdbcimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.daoapi.IOrderRewardPercentDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderRewardPercent;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.OrderRewardPercentFilter;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.OrderRewardPercent;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.PreparedStatementAction;

@Repository
public class OrderRewardPercentDaoImpl extends AbstractDaoImpl<IOrderRewardPercent, Integer>
		implements IOrderRewardPercentDao {

	@Override
	public IOrderRewardPercent createEntity() {
		return new OrderRewardPercent();
	}

	@Override
	public void insert(final IOrderRewardPercent entity) {
		executeStatement(new PreparedStatementAction<IOrderRewardPercent>(
				String.format("insert into %s (percent) values(?)", getTableName()), true) {
			@Override
			public IOrderRewardPercent doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setBigDecimal(1, entity.getPercent());

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
	public void update(final IOrderRewardPercent entity) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public List<IOrderRewardPercent> find(final OrderRewardPercentFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final OrderRewardPercentFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	protected IOrderRewardPercent parseRow(final ResultSet resultSet) throws SQLException {
		final IOrderRewardPercent entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setPercent(resultSet.getBigDecimal("percent"));
		return entity;
	}

	@Override
	protected String getTableName() {
		return "order_reward_percent";
	}

}
