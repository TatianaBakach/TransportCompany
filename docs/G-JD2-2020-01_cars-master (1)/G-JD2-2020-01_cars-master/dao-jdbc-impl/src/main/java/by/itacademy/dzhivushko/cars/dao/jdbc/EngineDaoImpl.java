package by.itacademy.dzhivushko.cars.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import by.itacademy.dzhivushko.cars.dao.api.IEngineDao;
import by.itacademy.dzhivushko.cars.dao.api.entity.enums.EngineType;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IEngine;
import by.itacademy.dzhivushko.cars.dao.api.filter.EngineFilter;
import by.itacademy.dzhivushko.cars.dao.jdbc.entity.Engine;
import by.itacademy.dzhivushko.cars.dao.jdbc.util.PreparedStatementAction;
import by.itacademy.dzhivushko.cars.dao.jdbc.util.StatementAction;
@Repository
public class EngineDaoImpl extends AbstractDaoImpl<IEngine, Integer> implements IEngineDao {

	@Override
	public IEngine createEntity() {
		return new Engine();
	}

	@Override
	public void insert(final IEngine entity) {
		executeStatement(new PreparedStatementAction<IEngine>(String.format(
				"insert into %s (type, volume, title, created, updated) values(?,?,?,?,?)", getTableName()), true) {
			@Override
			public IEngine doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getType().name());
				pStmt.setInt(2, entity.getVolume());
				pStmt.setString(3, entity.getTitle());
				pStmt.setObject(4, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(5, entity.getUpdated(), Types.TIMESTAMP);

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
	public void update(final IEngine entity) {
		executeStatement(new PreparedStatementAction<IEngine>(
				String.format("update %s set title=?, updated=?, volume=?, type=? where id=?", getTableName())) {
			@Override
			public IEngine doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getTitle());
				pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(3, entity.getVolume());
				pStmt.setString(4, entity.getType().name());
				pStmt.setInt(5, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	protected IEngine parseRow(final ResultSet resultSet) throws SQLException {
		final IEngine entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setTitle(resultSet.getString("title"));
		entity.setVolume(resultSet.getInt("volume"));
		entity.setType(EngineType.valueOf(resultSet.getString("type")));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		return entity;
	}

	@Override
	public Set<IEngine> getByModel(final Integer id) {
		return executeStatement(new StatementAction<Set<IEngine>>() {
			@Override
			public Set<IEngine> doWithStatement(final Statement statement) throws SQLException {
				// @formatter:off
				statement.executeQuery(
						String.format("select * from %s e " + "inner join model_2_engine m2e on e.id=m2e.engine_id "
								+ "where m2e.model_id=%s", getTableName(), id));
				// @formatter:on
				final ResultSet resultSet = statement.getResultSet();

				final Set<IEngine> result = new HashSet<IEngine>();
				boolean hasNext = resultSet.next();
				while (hasNext) {
					result.add(parseRow(resultSet));
					hasNext = resultSet.next();
				}
				resultSet.close();

				return result;
			}
		});
	}

	@Override
	public List<IEngine> find(final EngineFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final EngineFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	protected String getTableName() {
		return "engine";
	}

}
