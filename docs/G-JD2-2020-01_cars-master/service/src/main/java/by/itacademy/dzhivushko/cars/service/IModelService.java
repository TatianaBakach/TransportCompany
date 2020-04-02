package by.itacademy.dzhivushko.cars.service;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.dzhivushko.cars.dao.api.entity.table.IModel;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IModelInfo;
import by.itacademy.dzhivushko.cars.dao.api.filter.ModelFilter;

public interface IModelService {

	IModel get(Integer id);

	List<IModel> getAll();

	@Transactional
	void save(IModel entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IModel createEntity();

	IModelInfo createInfoEntity();

	IModel getFullInfo(final Integer id);

	@Transactional
	void save(IModel entity, IModelInfo infoEntity);

	long getCount(ModelFilter filter);

	List<IModel> find(ModelFilter filter);
}
