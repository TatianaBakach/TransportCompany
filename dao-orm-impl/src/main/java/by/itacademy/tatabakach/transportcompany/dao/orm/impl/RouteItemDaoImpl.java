package by.itacademy.tatabakach.transportcompany.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.RouteItem;
import by.itacademy.tatabakach.transportcompany.daoapi.IRouteItemDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRouteItem;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.RouteItemFilter;

@Repository
public class RouteItemDaoImpl extends AbstractDaoImpl<IRouteItem, Integer> implements IRouteItemDao {


	protected RouteItemDaoImpl() {
		super(RouteItem.class);
	}

	@Override
	public IRouteItem createEntity() {
		return new RouteItem();
	}

	@Override
	public List<IRouteItem> find(RouteItemFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(RouteItemFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IRouteItem getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
