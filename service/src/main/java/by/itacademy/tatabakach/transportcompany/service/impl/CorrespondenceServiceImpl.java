package by.itacademy.tatabakach.transportcompany.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.tatabakach.transportcompany.daoapi.ICorrespondenceDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICorrespondence;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CorrespondenceFilter;
import by.itacademy.tatabakach.transportcompany.service.ICorrespondenceService;

@Service
public class CorrespondenceServiceImpl implements ICorrespondenceService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CorrespondenceServiceImpl.class);

	@Autowired
	private ICorrespondenceDao dao;

	@Override
	public ICorrespondence createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final ICorrespondence entity) {
		if (entity.getId() == null) {
			dao.insert(entity);
			LOGGER.info("new correspondence create: {}", entity);
		} else {
			dao.update(entity);
			LOGGER.debug("correspondence update: {}", entity);
		}
	}

	@Override
	public ICorrespondence get(final Integer id) {
		final ICorrespondence entity = dao.get(id);
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
		LOGGER.info("delete all correspondence entities");
		dao.deleteAll();
	}

	@Override
	public List<ICorrespondence> getAll() {
		final List<ICorrespondence> all = dao.selectAll();
		LOGGER.debug("total count in DB: {}", all.size());
		return all;
	}

	@Override
	public List<ICorrespondence> find(CorrespondenceFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(CorrespondenceFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public ICorrespondence getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}

}
