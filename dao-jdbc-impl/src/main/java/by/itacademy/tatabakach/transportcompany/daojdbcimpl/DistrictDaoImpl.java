package by.itacademy.tatabakach.transportcompany.daojdbcimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.daoapi.IDistrictDao;
import by.itacademy.tatabakach.transportcompany.daoapi.IRegionDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDistrict;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRegion;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.DistrictFilter;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.District;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Region;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.PreparedStatementAction;

@Repository
public class DistrictDaoImpl extends AbstractDaoImpl<IDistrict, Integer> implements IDistrictDao {
	
	@Autowired
	private IRegionDao regionDao;
	
	@Override
	public IDistrict createEntity() {
		return new District();
	}

	@Override
	public void insert(final IDistrict entity) {
		executeStatement(new PreparedStatementAction<IDistrict>(
				String.format("insert into %s (name, region_id) values(?,?)",
						getTableName()),
				true) {
			@Override
			public IDistrict doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
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
	public void update(final IDistrict entity) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}
	
	@Override
	public List<IDistrict> find(final DistrictFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final DistrictFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	protected IDistrict parseRow(final ResultSet resultSet) throws SQLException {
		final IDistrict entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));

		final IRegion region = new Region();
		region.setId((Integer) resultSet.getObject("region_id"));
		entity.setRegion(region);;
		return entity;
	}

	@Override
	public IDistrict getFullInfo(final Integer id) {
		final IDistrict district = get(id);

		if (district.getRegion() != null) {
			district.setRegion(regionDao.get(district.getRegion().getId()));
		}

		return district;
	}

	@Override
	protected String getTableName() {
		return "district";
	}

}
