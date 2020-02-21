package by.itacademy.tatabakach.transportcompany.daojdbcimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.ICarDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICar;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CarFilter;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Car;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.PreparedStatementAction;

public class CarDaoImpl extends AbstractDaoImpl<ICar, Integer> implements ICarDao {

	@Override
	public ICar createEntity() {
		return new Car();
	}

	@Override
	public void insert(final ICar entity) {
		executeStatement(new PreparedStatementAction<ICar>(
				String.format("insert into %s (model, number) values(?,?)", getTableName()), true) {
			@Override
			public ICar doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getModel());
				pStmt.setObject(2, entity.getNumber());

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
	public void update(final ICar entity) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public List<ICar> find(final CarFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final CarFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	protected ICar parseRow(final ResultSet resultSet) throws SQLException {
		final ICar entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setModel(resultSet.getString("model"));
		entity.setNumber(resultSet.getString("number"));
		return entity;
	}

	@Override
	protected String getTableName() {
		return "car";
	}

}
