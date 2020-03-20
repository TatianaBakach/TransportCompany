package by.itacademy.tatabakach.transportcompany.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.tatabakach.transportcompany.daoapi.IRouteItemDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRouteItem;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.RouteItemFilter;
import by.itacademy.tatabakach.transportcompany.service.IRouteItemService;

@Service
public class RouteItemServiceImpl implements IRouteItemService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RouteItemServiceImpl.class);

	@Autowired
	private IRouteItemDao dao;

	@Override
	public IRouteItem createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IRouteItem entity) {
		if (entity.getId() == null) {
			dao.insert(entity);
			LOGGER.info("new route item create: {}", entity);
		} else {
			dao.update(entity);
			LOGGER.debug("route item update: {}", entity);
		}
	}

	@Override
	public IRouteItem get(final Integer id) {
		final IRouteItem entity = dao.get(id);
		LOGGER.debug("entityById: {}", entity);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		LOGGER.info("delete entity: {}", id);
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all route item entities");
		dao.deleteAll();
	}

	@Override
	public List<IRouteItem> getAll() {
		final List<IRouteItem> all = dao.selectAll();
		LOGGER.debug("total count in DB: {}", all.size());
		return all;
	}

	@Override
	public List<IRouteItem> find(RouteItemFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(RouteItemFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public IRouteItem getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}

}
