package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICar;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CarFilter;

public interface ICarService {

	ICar get(Integer id);

	List<ICar> getAll();

	@Transactional
	void save(ICar entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();
	
	ICar createEntity();

	List<ICar> find(CarFilter filter);

	long getCount(CarFilter filter);

}
