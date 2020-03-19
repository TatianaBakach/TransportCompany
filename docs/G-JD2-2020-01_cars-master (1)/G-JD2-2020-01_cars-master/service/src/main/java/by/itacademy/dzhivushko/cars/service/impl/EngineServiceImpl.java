package by.itacademy.dzhivushko.cars.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.dzhivushko.cars.dao.api.IEngineDao;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IEngine;
import by.itacademy.dzhivushko.cars.dao.api.filter.EngineFilter;
import by.itacademy.dzhivushko.cars.dao.jdbc.EngineDaoImpl;
import by.itacademy.dzhivushko.cars.service.IEngineService;
@Service
public class EngineServiceImpl implements IEngineService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EngineServiceImpl.class);
    @Autowired
    private IEngineDao dao;

    @Override
    public IEngine createEntity() {
        return dao.createEntity();
    }

    @Override
    public void save(final IEngine entity) {
        final Date modifedOn = new Date();
        entity.setUpdated(modifedOn);
        if (entity.getId() == null) {
            entity.setCreated(modifedOn);
            dao.insert(entity);
            LOGGER.info("new saved engine: {}", entity);
        } else {
            dao.update(entity);
        }
    }

    @Override
    public IEngine get(final Integer id) {
        final IEngine entity = dao.get(id);
        LOGGER.debug("entityById: {}", entity);
        return entity;
    }

    @Override
    public void delete(final Integer id) {
        LOGGER.info("delete entity: {}", id);
        dao.delete(id);
    }

    @Override
    public void deleteAll() {
        LOGGER.info("delete all brand entities");
        dao.deleteAll();
    }

    @Override
    public List<IEngine> getAll() {
        final List<IEngine> all = dao.selectAll();
        LOGGER.debug("total count in DB: {}", all.size());
        return all;
    }

    @Override
    public List<IEngine> find(EngineFilter filter) {
        return dao.find(filter);
    }

    @Override
    public long getCount(EngineFilter filter) {
        return dao.getCount(filter);
    }

}
