package by.itacademy.tatabakach.transportcompany.daojdbcimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.daoapi.ICountryDao;
import by.itacademy.tatabakach.transportcompany.daoapi.IRegionDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICountry;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRegion;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.RegionFilter;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Country;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Region;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.PreparedStatementAction;

@Repository
public class RegionDaoImpl extends AbstractDaoImpl<IRegion, Integer> implements IRegionDao {

	@Autowired
	private ICountryDao countryDao;
	
	@Override
	public IRegion createEntity() {
		return new Region();
	}

	@Override
	public void insert(final IRegion entity) {
		executeStatement(new PreparedStatementAction<IRegion>(
				String.format("insert into %s (name, country_id) values(?,?)",
						getTableName()),
				true) {
			@Override
			public IRegion doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setInt(2, entity.getCountry().getId());

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
	public void update(final IRegion entity) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}
	
	@Override
	public List<IRegion> find(final RegionFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final RegionFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	protected IRegion parseRow(final ResultSet resultSet) throws SQLException {
		final IRegion entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));

		final ICountry country = new Country();
		country.setId((Integer) resultSet.getObject("country_id"));
		entity.setCountry(country);;
		return entity;
	}

	@Override
	public IRegion getFullInfo(final Integer id) {
		final IRegion region = get(id);

		if (region.getCountry() != null) {
			region.setCountry(countryDao.get(region.getCountry().getId()));
		}

		return region;
	}

	@Override
	protected String getTableName() {
		return "region";
	}

}
