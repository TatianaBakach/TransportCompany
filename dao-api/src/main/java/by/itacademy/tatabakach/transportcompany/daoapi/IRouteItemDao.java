package by.itacademy.tatabakach.transportcompany.daoapi;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRouteItem;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.RouteItemFilter;

public interface IRouteItemDao extends IDao<IRouteItem, Integer>{
	
	List<IRouteItem> find(RouteItemFilter filter);

	long getCount(RouteItemFilter filter);

	IRouteItem getFullInfo(Integer id);

}
