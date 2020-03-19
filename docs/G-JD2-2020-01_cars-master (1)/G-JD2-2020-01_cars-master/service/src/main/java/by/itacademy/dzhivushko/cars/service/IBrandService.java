package by.itacademy.dzhivushko.cars.service;

import java.util.List;

import by.itacademy.dzhivushko.cars.dao.api.entity.table.IBrand;
import by.itacademy.dzhivushko.cars.dao.api.filter.BrandFilter;

public interface IBrandService {

	IBrand get(Integer id);

	List<IBrand> getAll();

	void save(IBrand entity);

	void delete(Integer id);

	void deleteAll();

	IBrand createEntity();

	List<IBrand> find(BrandFilter filter);

	long getCount(BrandFilter filter);

}
