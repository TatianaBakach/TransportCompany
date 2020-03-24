package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.EmployeeFilter;

public interface IEmployeeService {
	
	IEmployee get(Integer id);

	List<IEmployee> getAll();
	
	IEmployee getFullInfo(Integer id);

	@Transactional
	void save(IEmployee entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IEmployee createEntity();
	
	List<IEmployee> find(EmployeeFilter filter);

	long getCount(EmployeeFilter filter);

}
