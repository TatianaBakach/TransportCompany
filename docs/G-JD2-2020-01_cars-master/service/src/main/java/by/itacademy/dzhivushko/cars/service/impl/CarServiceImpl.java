package by.itacademy.dzhivushko.cars.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.dzhivushko.cars.dao.api.ICarDao;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.ICar;
import by.itacademy.dzhivushko.cars.dao.api.filter.CarFilter;
import by.itacademy.dzhivushko.cars.service.ICarService;

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
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
			LOGGER.info("new saved car: {}", entity);
		} else {
			dao.update(entity);
		}
	}

	@Override
	public ICar get(final Integer id) {
		final ICar entity = dao.get(id);
		LOGGER.debug("entityById: {}", entity);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		LOGGER.info("delete entity: {}", id);
		dao.delete(id);
	}

	@Override
	public List<ICar> getAll() {
		final List<ICar> all = dao.selectAll();
		LOGGER.debug("total count in DB: {}", all.size());
		return all;
	}

	@Override
	public List<ICar> find(final CarFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(final CarFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public ICar getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}

	@Override
	public ICar getNewestCar() {
		return dao.getNewestCar();
	}

}
