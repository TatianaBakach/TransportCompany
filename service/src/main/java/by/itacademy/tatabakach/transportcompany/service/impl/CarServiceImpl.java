package by.itacademy.tatabakach.transportcompany.service.impl;

import java.util.Date;
import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.ICarDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICar;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.CarDaoImpl;
import by.itacademy.tatabakach.transportcompany.service.ICarService;

public class CarServiceImpl implements ICarService {

	private ICarDao dao = new CarDaoImpl();

	@Override
	public ICar createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final ICar entity) {
		if (entity.getId() == null) {
			dao.insert(entity);
		} else {
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
		dao.deleteAll();
	}

	@Override
	public List<ICar> getAll() {
		final List<ICar> all = dao.selectAll();
		return all;
	}

}
