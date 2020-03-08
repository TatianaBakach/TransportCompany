package by.itacademy.tatabakach.transportcompany.daojdbcimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.daoapi.IDistrictDao;
import by.itacademy.tatabakach.transportcompany.daoapi.ILocalityDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDistrict;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ILocality;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.LocalityFilter;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.District;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Locality;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.PreparedStatementAction;

@Repository
public class LocalityDaoImpl extends AbstractDaoImpl<ILocality, Integer> implements ILocalityDao {

	@Autowired
	private IDistrictDao districtDao;

	@Override
	public ILocality createEntity() {
		return new Locality();
	}

	@Override
	public void insert(final ILocality entity) {
		executeStatement(new PreparedStatementAction<ILocality>(
				String.format("insert into %s (name, district_id) values(?,?)", getTableName()), true) {
			@Override
			public ILocality doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setInt(2, entity.getDistrict().getId());

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

		final IDistrict district = new District();
		district.setId((Integer) resultSet.getObject("district_id"));
		entity.setDistrict(district);
		;
		return entity;
	}

	@Override
	public ILocality getFullInfo(final Integer id) {
		final ILocality locality = get(id);

		if (locality.getDistrict() != null) {
			locality.setDistrict(districtDao.get(locality.getDistrict().getId()));
		}

		return locality;
	}

	@Override
	protected String getTableName() {
		return "locality";
	}

}
