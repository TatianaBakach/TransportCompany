package by.itacademy.dzhivushko.cars.service;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.dzhivushko.cars.dao.api.entity.table.IBrand;
import by.itacademy.dzhivushko.cars.dao.api.filter.BrandFilter;

public interface IBrandService {

	IBrand get(Integer id);

	List<IBrand> getAll();

	@Transactional
	void save(IBrand entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IBrand createEntity();

	List<IBrand> find(BrandFilter filter);

	long getCount(BrandFilter filter);

}
