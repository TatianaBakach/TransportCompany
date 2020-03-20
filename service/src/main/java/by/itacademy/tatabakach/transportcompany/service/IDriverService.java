package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDriver;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.DriverFilter;

public interface IDriverService {
	
	IDriver get(Integer id);

	List<IDriver> getAll();

	@Transactional
	void save(IDriver entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IDriver createEntity();
	
	List<IDriver> find(DriverFilter filter);
	
	long getCount(DriverFilter filter);

}
