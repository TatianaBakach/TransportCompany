package by.itacademy.dzhivushko.cars.dao.api;

import java.util.List;

import by.itacademy.dzhivushko.cars.dao.api.entity.table.IModel;
import by.itacademy.dzhivushko.cars.dao.api.filter.ModelFilter;

public interface IModelDao extends IDao<IModel, Integer> {

    IModel getFullInfo(final Integer id);

    List<IModel> find(ModelFilter filter);

    long getCount(ModelFilter filter);
}
