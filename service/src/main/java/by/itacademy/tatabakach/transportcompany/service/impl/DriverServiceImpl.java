package by.itacademy.tatabakach.transportcompany.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.tatabakach.transportcompany.daoapi.IDriverDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDriver;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.DriverDaoImpl;
import by.itacademy.tatabakach.transportcompany.service.IDriverService;

@Service
public class DriverServiceImpl implements IDriverService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DriverServiceImpl.class);

	@Autowired
	private IDriverDao dao;

	@Override
	public IDriver createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IDriver entity) {
		if (entity.getId() == null) {
			LOGGER.info("new driver create: {}", entity);
			dao.insert(entity);
		} else {
			LOGGER.debug("driver update: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public IDriver get(final Integer id) {
		final IDriver entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
	}

	@Override
	public List<IDriver> getAll() {
		final List<IDriver> all = dao.selectAll();
		return all;
	}

}
