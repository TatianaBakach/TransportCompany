package by.itacademy.tatabakach.transportcompany.daojdbcimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.daoapi.ICountryDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICountry;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CountryFilter;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Country;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.PreparedStatementAction;

@Repository
public class CountryDaoImpl extends AbstractDaoImpl<ICountry, Integer> implements ICountryDao {
	
	@Override
	public ICountry createEntity() {
		return new Country();
	}

	@Override
	public void insert(final ICountry entity) {
		executeStatement(new PreparedStatementAction<ICountry>(
				String.format("insert into %s (name) values(?)", getTableName()), true) {
			@Override
			public ICountry doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
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
	public void update(final ICountry entity) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public List<ICountry> find(final CountryFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final CountryFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	protected ICountry parseRow(final ResultSet resultSet) throws SQLException {
		final ICountry entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		return entity;
	}

	@Override
	protected String getTableName() {
		return "country";
	}


}
