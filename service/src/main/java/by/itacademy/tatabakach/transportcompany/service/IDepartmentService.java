package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDepartment;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.DepartmentFilter;

public interface IDepartmentService {
	
	IDepartment get(Integer id);

	List<IDepartment> getAll();

	void save(IDepartment entity);

	void delete(Integer id);

	void deleteAll();

	IDepartment createEntity();
	
	List<IDepartment> find(DepartmentFilter filter);

	long getCount(DepartmentFilter filter);

}
