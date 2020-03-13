package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.EmployeeFilter;

public interface IEmployeeService {
	
	IEmployee get(Integer id);

	List<IEmployee> getAll();

	void save(IEmployee entity);

	void delete(Integer id);

	void deleteAll();

	IEmployee createEntity();
	
	IEmployee getFullInfo(Integer id);

	List<IEmployee> find(EmployeeFilter filter);

	long getCount(EmployeeFilter filter);

}
