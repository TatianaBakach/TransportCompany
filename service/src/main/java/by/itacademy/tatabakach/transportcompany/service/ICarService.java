package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICar;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CarFilter;

public interface ICarService {

	ICar get(Integer id);

	List<ICar> getAll();

	void save(ICar entity);

	void delete(Integer id);

	void deleteAll();
	
	ICar createEntity();

	List<ICar> find(CarFilter filter);

	long getCount(CarFilter filter);

}
