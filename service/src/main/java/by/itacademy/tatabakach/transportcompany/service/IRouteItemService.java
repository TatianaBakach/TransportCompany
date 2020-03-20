package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRouteItem;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.RouteItemFilter;

public interface IRouteItemService {
	

	IRouteItem get(Integer id);

	List<IRouteItem> getAll();

	void save(IRouteItem entity);

	void delete(Integer id);

	void deleteAll();

	IRouteItem createEntity();
	
	IRouteItem getFullInfo(Integer id);

	List<IRouteItem> find(RouteItemFilter filter);

	long getCount(RouteItemFilter filter);

}
