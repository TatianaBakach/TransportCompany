package by.itacademy.tatabakach.transportcompany.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.tatabakach.transportcompany.daoapi.IRegionDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRegion;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.RegionFilter;
import by.itacademy.tatabakach.transportcompany.service.IRegionService;

@Service
public class RegionServiceImpl implements IRegionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegionServiceImpl.class);
	
	@Autowired
	private IRegionDao dao;

	@Override
	public IRegion createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IRegion entity) {
		if (entity.getId() == null) {
			LOGGER.info("new region create: {}", entity);
			dao.insert(entity);
		} else {
			LOGGER.debug("region update: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public IRegion get(final Integer id) {
		final IRegion entity = dao.get(id);
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
		LOGGER.info("delete all regions");
		dao.deleteAll();
	}


	@Override
	public List<IRegion> getAll() {
		final List<IRegion> all = dao.selectAll();
		LOGGER.debug("total count in DB: {}", all.size());
		return all;
	}

	@Override
	public List<IRegion> find(final RegionFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(final RegionFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public IRegion getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}

}
