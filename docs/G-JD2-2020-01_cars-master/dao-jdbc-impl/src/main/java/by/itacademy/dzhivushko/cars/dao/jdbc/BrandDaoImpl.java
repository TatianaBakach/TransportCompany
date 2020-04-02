package by.itacademy.dzhivushko.cars.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.dzhivushko.cars.dao.api.IBrandDao;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IBrand;
import by.itacademy.dzhivushko.cars.dao.api.filter.BrandFilter;
import by.itacademy.dzhivushko.cars.dao.jdbc.entity.Brand;
import by.itacademy.dzhivushko.cars.dao.jdbc.util.PreparedStatementAction;

@Repository
public class BrandDaoImpl extends AbstractDaoImpl<IBrand, Integer> implements IBrandDao {

	@Override
	public IBrand createEntity() {
		return new Brand();
	}

	@Override
	public void insert(final IBrand entity) {
		executeStatement(new PreparedStatementAction<IBrand>(
				String.format("insert into %s (name, created, updated) values(?,?,?)", getTableName()), true) {
			@Override
			public IBrand doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setObject(2, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);

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
	public void update(final IBrand entity) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public List<IBrand> find(final BrandFilter filter) {
		return selectAll();
	}

	@Override
	public long getCount(final BrandFilter filter) {
		return 10;// FIXME: temporary solution
	}

	@Override
	protected IBrand parseRow(final ResultSet resultSet) throws SQLException {
		final IBrand entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}

	@Override
	protected String getTableName() {
		return "brand";
	}
}
