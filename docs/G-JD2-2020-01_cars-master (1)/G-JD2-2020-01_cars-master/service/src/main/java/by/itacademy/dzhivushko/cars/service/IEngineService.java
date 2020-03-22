package by.itacademy.dzhivushko.cars.service;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.dzhivushko.cars.dao.api.entity.table.IEngine;
import by.itacademy.dzhivushko.cars.dao.api.filter.EngineFilter;

public interface IEngineService {

	IEngine get(Integer id);

	List<IEngine> getAll();

	@Transactional
	void save(IEngine entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IEngine createEntity();

	List<IEngine> find(EngineFilter filter);

	long getCount(EngineFilter filter);
}
