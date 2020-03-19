package by.itacademy.dzhivushko.cars.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.dzhivushko.cars.dao.api.IModelDao;
import by.itacademy.dzhivushko.cars.dao.api.IModelInfoDao;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IModel;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IModelInfo;
import by.itacademy.dzhivushko.cars.dao.api.filter.ModelFilter;
import by.itacademy.dzhivushko.cars.dao.jdbc.ModelDaoImpl;
import by.itacademy.dzhivushko.cars.dao.jdbc.ModelInfoDaoImpl;
import by.itacademy.dzhivushko.cars.service.IModelService;
@Service
public class ModelServiceImpl implements IModelService {
	@Autowired
    private IModelDao dao;
	@Autowired
    private IModelInfoDao modelInfoDao;

    @Override
    public IModelInfo createInfoEntity() {
        return modelInfoDao.createEntity();
    }

    @Override
    public IModel createEntity() {
        return dao.createEntity();
    }

    @Override
    public void save(final IModel entity) {
        final Date modifedOn = new Date();
        entity.setUpdated(modifedOn);
        if (entity.getId() == null) {
            entity.setCreated(modifedOn);
            dao.insert(entity);
        } else {
            dao.update(entity);
        }
    }

    @Override
    public IModel get(final Integer id) {
        final IModel entity = dao.get(id);
        return entity;
    }

    @Override
    public void delete(final Integer id) {
        // remove all references
        final IModel iModel = dao.get(id);
        iModel.getEngines().clear();
        dao.update(iModel);

        dao.delete(id);
    }

    @Override
    public void deleteAll() {
        modelInfoDao.deleteAll();
        dao.deleteAll();
    }

    @Override
    public List<IModel> getAll() {
        final List<IModel> all = dao.selectAll();
        return all;
    }

    @Override
    public IModel getFullInfo(final Integer id) {
        final IModel entity = dao.getFullInfo(id);
        return entity;
    }

    @Override
    public void save(final IModel model, final IModelInfo info) {
        final Date modifiedDate = new Date();
        model.setUpdated(modifiedDate);
        info.setUpdated(modifiedDate);

        if (model.getId() == null) {
            model.setCreated(modifiedDate);
            dao.insert(model);

            info.setId(model.getId());
            info.setCreated(modifiedDate);
            info.setModel(model);
            modelInfoDao.insert(info);

            model.setModelInfo(info);
        } else {
            dao.update(model);
            modelInfoDao.update(info);
        }
    }

    @Override
    public long getCount(ModelFilter filter) {
        return dao.getCount(filter);
    }

    @Override
    public List<IModel> find(ModelFilter filter) {
        return dao.find(filter);
    }

}
