package by.itacademy.tatabakach.transportcompany.daojdbcimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.daoapi.IDriverDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDriver;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.DriverFilter;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Driver;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.PreparedStatementAction;

@Repository
public class DriverDaoImpl extends AbstractDaoImpl<IDriver, Integer> implements IDriverDao {

	@Override
	public IDriver createEntity() {
		return new Driver();
	}

	@Override
	public void insert(final IDriver entity) {
		executeStatement(new PreparedStatementAction<IDriver>(
				String.format("insert into %s (firstName, middleName, lastName, passportData, phone) values(?,?,?,?,?)",
						getTableName()),
				true) {
			@Override
			public IDriver doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getFirstName());
				pStmt.setString(2, entity.getMiddleName());
				pStmt.setString(3, entity.getLastName());
				pStmt.setString(4, entity.getPassportData());
				pStmt.setString(5, entity.getPhone());

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
	public void update(final IDriver entity) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public List<IDriver> find(final DriverFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final DriverFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	protected IDriver parseRow(final ResultSet resultSet) throws SQLException {
		final IDriver entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setFirstName(resultSet.getString("firstName"));
		entity.setMiddleName(resultSet.getString("middleName"));
		entity.setLastName(resultSet.getString("lastName"));
		entity.setPassportData(resultSet.getString("passportData"));
		entity.setPhone(resultSet.getString("phone"));
		
		return entity;
	}

	@Override
	protected String getTableName() {
		return "driver";
	}

}
