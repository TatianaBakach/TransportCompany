package by.itacademy.dzhivushko.cars.service;

import java.util.List;

import by.itacademy.dzhivushko.cars.dao.api.entity.table.ICar;
import by.itacademy.dzhivushko.cars.dao.api.filter.CarFilter;

public interface ICarService {

    ICar get(Integer id);

    void save(ICar entity);

    void delete(Integer id);

    ICar createEntity();

    List<ICar> getAll();

    List<ICar> find(CarFilter filter);

    long getCount(CarFilter filter);

    ICar getFullInfo(Integer id);

}
