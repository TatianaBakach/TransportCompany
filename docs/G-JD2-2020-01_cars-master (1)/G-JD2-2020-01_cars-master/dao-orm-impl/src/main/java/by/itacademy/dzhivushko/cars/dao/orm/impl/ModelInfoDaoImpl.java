package by.itacademy.dzhivushko.cars.dao.orm.impl;

import org.springframework.stereotype.Repository;

import by.itacademy.dzhivushko.cars.dao.api.IModelInfoDao;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IModelInfo;
import by.itacademy.dzhivushko.cars.dao.orm.impl.entity.ModelInfo;

@Repository
public class ModelInfoDaoImpl extends AbstractDaoImpl<IModelInfo, Integer> implements IModelInfoDao {

    protected ModelInfoDaoImpl() {
        super(ModelInfo.class);
    }

    @Override
    public IModelInfo createEntity() {
        return new ModelInfo();
    }

}
