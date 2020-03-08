package by.itacademy.tatabakach.transportcompany.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.tatabakach.transportcompany.daoapi.ILocalityDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ILocality;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.LocalityFilter;
import by.itacademy.tatabakach.transportcompany.service.ILocalityService;

@Service
public class LocalityServiceImpl implements ILocalityService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LocalityServiceImpl.class);

	@Autowired
	private ILocalityDao dao;

	@Override
	public ILocality createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final ILocality entity) {
		if (entity.getId() == null) {
			LOGGER.info("new locality create: {}", entity);
			dao.insert(entity);
		} else {
			LOGGER.debug("locality update: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public ILocality get(final Integer id) {
		final ILocality entity = dao.get(id);
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
		LOGGER.info("delete all localities");
		dao.deleteAll();
	}

	@Override
	public List<ILocality> getAll() {
		final List<ILocality> all = dao.selectAll();
		LOGGER.debug("total count in DB: {}", all.size());
		return all;
	}

	@Override
	public List<ILocality> find(final LocalityFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(final LocalityFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public ILocality getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}

}
