package by.itacademy.dzhivushko.cars.dao.api;

import java.util.List;

import by.itacademy.dzhivushko.cars.dao.api.entity.table.IBrand;
import by.itacademy.dzhivushko.cars.dao.api.filter.BrandFilter;

public interface IBrandDao extends IDao<IBrand, Integer> {
	List<IBrand> find(BrandFilter filter);

	long getCount(BrandFilter filter);
}
