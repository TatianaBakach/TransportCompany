package by.itacademy.tatabakach.transportcompany.daojdbcimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.daoapi.IPositionDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IPosition;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.PositionFilter;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Position;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.PreparedStatementAction;

@Repository
public class PositionDaoImpl extends AbstractDaoImpl<IPosition, Integer> implements IPositionDao {

	@Override
	public IPosition createEntity() {
		return new Position();
	}

	@Override
	public void insert(final IPosition entity) {
		executeStatement(new PreparedStatementAction<IPosition>(
				String.format("insert into %s (name) values(?)", getTableName()), true) {
			@Override
			public IPosition doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());

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
	public void update(final IPosition entity) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public List<IPosition> find(final PositionFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final PositionFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	protected IPosition parseRow(final ResultSet resultSet) throws SQLException {
		final IPosition entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		return entity;
	}

	@Override
	protected String getTableName() {
		return "position";
	}

}
