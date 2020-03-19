package by.itacademy.dzhivushko.cars.service;

import java.util.List;

import by.itacademy.dzhivushko.cars.dao.api.entity.table.IModel;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IModelInfo;
import by.itacademy.dzhivushko.cars.dao.api.filter.ModelFilter;

public interface IModelService {

    IModel get(Integer id);

    List<IModel> getAll();

    void save(IModel entity);

    void delete(Integer id);

    void deleteAll();

    IModel createEntity();

    IModelInfo createInfoEntity();

    IModel getFullInfo(final Integer id);

    void save(IModel entity, IModelInfo infoEntity);

    long getCount(ModelFilter filter);

    List<IModel> find(ModelFilter filter);
}
