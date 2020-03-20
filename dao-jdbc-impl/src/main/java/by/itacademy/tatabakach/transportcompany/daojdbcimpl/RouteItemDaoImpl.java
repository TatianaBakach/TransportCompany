package by.itacademy.tatabakach.transportcompany.daojdbcimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.daoapi.IAddressDao;
import by.itacademy.tatabakach.transportcompany.daoapi.IOrderDao;
import by.itacademy.tatabakach.transportcompany.daoapi.IRouteItemDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IAddress;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRouteItem;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.RouteItemFilter;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Address;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Order;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.RouteItem;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.PreparedStatementAction;

@Repository
public class RouteItemDaoImpl extends AbstractDaoImpl<IRouteItem, Integer> implements IRouteItemDao {

	@Autowired
	private IOrderDao orderDao;
	
	@Autowired
	private IAddressDao addressDao;

	@Override
	public IRouteItem createEntity() {
		return new RouteItem();
	}

	@Override
	public void insert(final IRouteItem entity) {
		executeStatement(new PreparedStatementAction<IRouteItem>(String.format(
				"insert into %s (order_id, address_id, date, cargo_weight, cargo_volume, custom_id, contact_person, contact_phone) values(?,?,?,?,?,?,?,?)", getTableName()), true) {
			@Override
			public IRouteItem doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getOrder().getId());
				pStmt.setInt(2, entity.getAddress().getId());
				pStmt.setObject(3, entity.getDate(), Types.TIMESTAMP);
				pStmt.setString(4, entity.getCargoWeight());
				pStmt.setString(5, entity.getCargoVolume());
				pStmt.setInt(6, entity.getCustom().getId());
				pStmt.setString(7, entity.getContactPerson());
				pStmt.setString(8, entity.getContactPhone());

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
	public void update(final IRouteItem entity) {
		throw new RuntimeException("will be implemented in ORM layer");
	}

	@Override
	protected IRouteItem parseRow(final ResultSet resultSet) throws SQLException {
		final IRouteItem entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));

		final IOrder order = new Order();
		order.setId((Integer) resultSet.getObject("order_id"));
		entity.setOrder(order);;
		
		final IAddress address = new Address();
		address.setId((Integer) resultSet.getObject("address_id"));
		entity.setAddress(address);

		entity.setDate(resultSet.getTimestamp("date"));
		entity.setCargoWeight(resultSet.getString("cargo_weight"));
		entity.setCargoVolume(resultSet.getString("cargo_volume"));
		
		final IAddress custom = new Address();
		custom.setId((Integer) resultSet.getObject("custom_id"));
		entity.setCustom(custom);
		
		entity.setContactPerson(resultSet.getString("contact_person"));
		entity.setContactPhone(resultSet.getString("contact_phone"));


		return entity;
	}

	@Override
	public List<IRouteItem> find(final RouteItemFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final RouteItemFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public IRouteItem getFullInfo(final Integer id) {
		final IRouteItem routeItem = get(id);

		if (routeItem.getOrder() != null) {
			routeItem.setOrder(orderDao.get(routeItem.getOrder().getId()));
		}
		if (routeItem.getAddress() != null) {
			routeItem.setAddress(addressDao.get(routeItem.getAddress().getId()));
		}
		
		if (routeItem.getCustom() != null) {
			routeItem.setCustom(addressDao.get(routeItem.getCustom().getId()));
		}

		return routeItem;
	}

	@Override
	protected String getTableName() {
		return "route_item";
	}

}
