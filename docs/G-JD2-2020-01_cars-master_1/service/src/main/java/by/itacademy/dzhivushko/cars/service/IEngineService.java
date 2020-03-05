package by.itacademy.dzhivushko.cars.service;

import java.util.List;

import by.itacademy.dzhivushko.cars.dao.api.entity.table.IEngine;
import by.itacademy.dzhivushko.cars.dao.api.filter.EngineFilter;

public interface IEngineService {

    IEngine get(Integer id);

    List<IEngine> getAll();

    void save(IEngine entity);

    void delete(Integer id);

    void deleteAll();

    IEngine createEntity();

    List<IEngine> find(EngineFilter filter);

    long getCount(EngineFilter filter);
}
