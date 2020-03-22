package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRouteItem;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.RouteItemFilter;

public interface IRouteItemService {
	

	IRouteItem get(Integer id);

	List<IRouteItem> getAll();

	@Transactional
	void save(IRouteItem entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IRouteItem createEntity();
	
	IRouteItem getFullInfo(Integer id);

	List<IRouteItem> find(RouteItemFilter filter);

	long getCount(RouteItemFilter filter);

}
