package by.itacademy.dzhivushko.cars.dao.api;

import java.util.List;

import by.itacademy.dzhivushko.cars.dao.api.entity.table.ICar;
import by.itacademy.dzhivushko.cars.dao.api.filter.CarFilter;

public interface ICarDao extends IDao<ICar, Integer> {

	List<ICar> find(CarFilter filter);

	long getCount(CarFilter filter);

	ICar getFullInfo(Integer id);

	ICar getNewestCar();

}
