package by.itacademy.tatabakach.transportcompany.daojdbcimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;

import by.itacademy.tatabakach.transportcompany.daoapi.IDao;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.PreparedStatementAction;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.SQLExecutionException;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.StatementAction;

public abstract class AbstractDaoImpl<ENTITY, ID> implements IDao<ENTITY, ID> {

	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.user}")
	private String user;
	@Value("${jdbc.password}")
	private String password;

	@PostConstruct
	private void init() {

		if (url == null) {
			throw new IllegalArgumentException("[url] cant be null");
		}

		if (password == null) {
			throw new IllegalArgumentException("[password] cant be null");
		}

		if (user == null) {
			throw new IllegalArgumentException("[user] cant be null");
		}
	}
	
	@PreDestroy
	private void clean() {

	}

	@Override
	public ENTITY get(final ID id) {
		StatementAction<ENTITY> action = (statement) -> {
			statement.executeQuery("select * from " + getTableName() + " where id=" + id);

			final ResultSet resultSet = statement.getResultSet();

			final boolean hasNext = resultSet.next();
			ENTITY result = null;
			if (hasNext) {
				result = parseRow(resultSet);
			}

			resultSet.close();
			return result;
		};
		ENTITY entityById = executeStatement(action);
		return entityById;
	}

	@Override
	public List<ENTITY> selectAll() {
		StatementAction<List<ENTITY>> action = new StatementAction<List<ENTITY>>() {
			@Override
			public List<ENTITY> doWithStatement(final Statement statement) throws SQLException {
				statement.executeQuery("select * from " + getTableName());

				final ResultSet resultSet = statement.getResultSet();
				final List<ENTITY> result = new ArrayList<>();
				boolean hasNext = resultSet.next();
				while (hasNext) {
					result.add(parseRow(resultSet));
					hasNext = resultSet.next();
				}
				resultSet.close();
				return result;
			}
		};
		return executeStatement(action);
	}

	@Override
	public void delete(final ID id) {
		executeStatement(
				new PreparedStatementAction<Integer>(String.format("delete from %s where id=?", getTableName())) {
					@Override
					public Integer doWithPreparedStatement(final PreparedStatement prepareStatement)
							throws SQLException {
						prepareStatement.setObject(1, id);
						return prepareStatement.executeUpdate();
					}
				});
	}

	@Override
	public void deleteAll() {
		executeStatement(new PreparedStatementAction<Integer>("delete from " + getTableName()) {
			@Override
			public Integer doWithPreparedStatement(final PreparedStatement prepareStatement) throws SQLException {
				final int executeUpdate = prepareStatement.executeUpdate();
				return executeUpdate;
			}
		});
	}

	protected <T> T executeStatement(final StatementAction<T> action) {
		try (Connection c = getConnection(); Statement stmt = c.createStatement()) {
			c.setAutoCommit(false);
			return action.doWithStatement(stmt);

		} catch (final SQLException e) {
			throw new SQLExecutionException(e); // wrap catchable exception with
			// runtime
		}
	}

	protected <T> T executeStatement(final PreparedStatementAction<T> action) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = action.isReturnGeneratedKeys()
						? c.prepareStatement(action.getSql(), Statement.RETURN_GENERATED_KEYS)
						: c.prepareStatement(action.getSql())) {
			c.setAutoCommit(false);
			try {
				final T doWithPreparedStatement = action.doWithPreparedStatement(pStmt);
				c.commit();
				return doWithPreparedStatement;
			} catch (final Exception e) {
				c.rollback();
				throw new RuntimeException(e);
			}

		} catch (final SQLException e) {
			throw new SQLExecutionException(e);
		}
	}

	protected Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	protected abstract ENTITY parseRow(final ResultSet resultSet) throws SQLException;


	protected abstract String getTableName();
}