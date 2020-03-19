package by.itacademy.tatabakach.transportcompany.daojdbcimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.daoapi.ILocalityDao;
import by.itacademy.tatabakach.transportcompany.daoapi.IRegionDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ILocality;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRegion;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.LocalityFilter;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Locality;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Region;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.PreparedStatementAction;

@Repository
public class LocalityDaoImpl extends AbstractDaoImpl<ILocality, Integer> implements ILocalityDao {

	@Autowired
	private IRegionDao regionDao;

	@Override
	public ILocality createEntity() {
		return new Locality();
	}

	@Override
	public void insert(final ILocality entity) {
		executeStatement(new PreparedStatementAction<ILocality>(
				String.format("insert into %s (name, region_id) values(?,?)", getTableName()), true) {
			@Override
			public ILocality doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setInt(2, entity.getRegion().getId());

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
	public void update(final ILocality entity) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public List<ILocality> find(final LocalityFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final LocalityFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	protected ILocality parseRow(final ResultSet resultSet) throws SQLException {
		final ILocality entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));

		final IRegion region = new Region();
		region.setId((Integer) resultSet.getObject("region_id"));
		entity.setRegion(region);
		;
		return entity;
	}

	@Override
	public ILocality getFullInfo(final Integer id) {
		final ILocality locality = get(id);

		if (locality.getRegion() != null) {
			locality.setRegion(regionDao.get(locality.getRegion().getId()));
		}

		return locality;
	}

	@Override
	protected String getTableName() {
		return "locality";
	}

}
