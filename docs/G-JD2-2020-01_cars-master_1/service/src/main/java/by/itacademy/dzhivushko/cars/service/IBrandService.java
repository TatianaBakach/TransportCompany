package by.itacademy.dzhivushko.cars.service;

import java.util.List;

import by.itacademy.dzhivushko.cars.dao.api.entity.table.IBrand;

public interface IBrandService {

	IBrand get(Integer id);

	List<IBrand> getAll();

	void save(IBrand entity);

	void delete(Integer id);

	void deleteAll();

	IBrand createEntity();

}
