package by.itacademy.tatabakach.transportcompany.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.tatabakach.transportcompany.daoapi.IPositionDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IPosition;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.PositionFilter;
import by.itacademy.tatabakach.transportcompany.service.IPositionService;

@Service
public class PositionServiceImpl implements IPositionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PositionServiceImpl.class);

	@Autowired
	private IPositionDao dao;

	@Override
	public IPosition createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IPosition entity) {
		if (entity.getId() == null) {
			LOGGER.info("new position create: {}", entity);
			dao.insert(entity);
		} else {
			LOGGER.debug("position update: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public IPosition get(final Integer id) {
		final IPosition entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all positions");
		dao.deleteAll();
	}

	@Override
	public List<IPosition> find(final PositionFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(final PositionFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public List<IPosition> getAll() {
		final List<IPosition> all = dao.selectAll();
		return all;
	}

}
