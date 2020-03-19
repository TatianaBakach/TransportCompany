package by.itacademy.tatabakach.transportcompany.daojdbcimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.daoapi.ITaxDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITax;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.TaxFilter;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Tax;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.PreparedStatementAction;

@Repository
public class TaxDaoImpl extends AbstractDaoImpl<ITax, Integer> implements ITaxDao {

	@Override
	public ITax createEntity() {
		return new Tax();
	}

	@Override
	public void insert(final ITax entity) {
		executeStatement(new PreparedStatementAction<ITax>(
				String.format("insert into %s (name, rate) values(?,?)", getTableName()), true) {
			@Override
			public ITax doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setBigDecimal(2, entity.getRate());

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
	public void update(final ITax entity) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public List<ITax> find(final TaxFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final TaxFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	protected ITax parseRow(final ResultSet resultSet) throws SQLException {
		final ITax entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setRate(resultSet.getBigDecimal("rate"));
		return entity;
	}

	@Override
	protected String getTableName() {
		return "tax";
	}

}
