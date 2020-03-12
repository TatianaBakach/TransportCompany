package by.itacademy.tatabakach.transportcompany.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.tatabakach.transportcompany.daoapi.ICfrDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICfr;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CfrFilter;
import by.itacademy.tatabakach.transportcompany.service.ICfrService;

@Service
public class CfrServiceImpl implements ICfrService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CfrServiceImpl.class);

	@Autowired
	private ICfrDao dao;

	@Override
	public ICfr createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final ICfr entity) {
		if (entity.getId() == null) {
			dao.insert(entity);
			LOGGER.info("new cfr create: {}", entity);
		} else {
			dao.update(entity);
			LOGGER.debug("cfr update: {}", entity);
		}
	}

	@Override
	public ICfr get(final Integer id) {
		final ICfr entity = dao.get(id);
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
		LOGGER.info("delete all cfr entities");
		dao.deleteAll();
	}

	@Override
	public List<ICfr> getAll() {
		final List<ICfr> all = dao.selectAll();
		LOGGER.debug("total count in DB: {}", all.size());
		return all;
	}

	@Override
	public List<ICfr> find(CfrFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(CfrFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public ICfr getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}

}
