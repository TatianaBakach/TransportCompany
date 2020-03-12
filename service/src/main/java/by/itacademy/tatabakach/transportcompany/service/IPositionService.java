package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IPosition;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.PositionFilter;

public interface IPositionService {

	IPosition get(Integer id);

	List<IPosition> getAll();

	void save(IPosition entity);

	void delete(Integer id);

	void deleteAll();

	IPosition createEntity();

	List<IPosition> find(PositionFilter filter);

	long getCount(PositionFilter filter);

}
