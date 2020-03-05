package by.itacademy.tatabakach.transportcompany.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.tatabakach.transportcompany.daoapi.ICarDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICar;
import by.itacademy.tatabakach.transportcompany.service.ICarService;

@Service
public class CarServiceImpl implements ICarService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CarServiceImpl.class);

	@Autowired
	private ICarDao dao;

	@Override
	public ICar createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final ICar entity) {
		if (entity.getId() == null) {
			LOGGER.info("new car create: {}", entity);
			dao.insert(entity);
		} else {
			LOGGER.debug("car update: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public ICar get(final Integer id) {
		final ICar entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all cars");
		dao.deleteAll();
	}

	@Override
	public List<ICar> getAll() {
		final List<ICar> all = dao.selectAll();
		return all;
	}

}
