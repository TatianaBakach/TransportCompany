package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDriver;

public interface IDriverService {
	
	IDriver get(Integer id);

	List<IDriver> getAll();

	void save(IDriver entity);

	void delete(Integer id);

	void deleteAll();

	IDriver createEntity();

}
