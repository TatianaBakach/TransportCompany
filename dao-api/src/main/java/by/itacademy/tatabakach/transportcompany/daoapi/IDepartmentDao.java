package by.itacademy.tatabakach.transportcompany.daoapi;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDepartment;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.DepartmentFilter;

public interface IDepartmentDao extends IDao<IDepartment, Integer> {
	
	List<IDepartment> find(DepartmentFilter filter);

	long getCount(DepartmentFilter filter);

}
