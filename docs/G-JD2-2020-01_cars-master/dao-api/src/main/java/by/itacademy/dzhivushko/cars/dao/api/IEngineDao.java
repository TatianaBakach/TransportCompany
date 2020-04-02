package by.itacademy.dzhivushko.cars.dao.api;

import java.util.List;
import java.util.Set;

import by.itacademy.dzhivushko.cars.dao.api.entity.table.IEngine;
import by.itacademy.dzhivushko.cars.dao.api.filter.EngineFilter;

public interface IEngineDao extends IDao<IEngine, Integer> {

    Set<IEngine> getByModel(Integer id);

    List<IEngine> find(EngineFilter filter);

    long getCount(EngineFilter filter);

}
