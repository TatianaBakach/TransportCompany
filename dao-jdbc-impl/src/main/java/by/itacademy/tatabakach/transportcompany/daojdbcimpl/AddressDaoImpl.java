package by.itacademy.tatabakach.transportcompany.daojdbcimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.daoapi.IAddressDao;
import by.itacademy.tatabakach.transportcompany.daoapi.ILocalityDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IAddress;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ILocality;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.AddressFilter;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Address;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Locality;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.PreparedStatementAction;

@Repository
public class AddressDaoImpl extends AbstractDaoImpl<IAddress, Integer> implements IAddressDao {

	@Autowired
	private ILocalityDao localityDao;

	@Override
	public IAddress createEntity() {
		return new Address();
	}

	@Override
	public void insert(final IAddress entity) {
		executeStatement(new PreparedStatementAction<IAddress>(String.format(
				"insert into %s (postcode, locality_id, exact_address, note) values(?,?,?,?)", getTableName()), true) {
			@Override
			public IAddress doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getPostcode());
				pStmt.setInt(2, entity.getLocality().getId());
				pStmt.setString(3, entity.getExactAddress());
				pStmt.setString(4, entity.getNote());

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
	public void update(final IAddress entity) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public List<IAddress> find(final AddressFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final AddressFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	protected IAddress parseRow(final ResultSet resultSet) throws SQLException {
		final IAddress entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setPostcode(resultSet.getString("postcode"));

		final ILocality locality = new Locality();
		locality.setId((Integer) resultSet.getObject("locality_id"));
		entity.setLocality(locality);

		entity.setExactAddress(resultSet.getString("exact_address"));
		entity.setNote(resultSet.getString("note"));
		return entity;
	}

	@Override
	public IAddress getFullInfo(final Integer id) {
		final IAddress address = get(id);

		if (address.getLocality() != null) {
			address.setLocality(localityDao.get(address.getLocality().getId()));
		}

		return address;
	}

	@Override
	protected String getTableName() {
		return "address";
	}

}
