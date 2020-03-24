package by.itacademy.tatabakach.transportcompany.daoapi;

import java.util.List;
import java.util.Set;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.EmployeeFilter;

public interface IEmployeeDao extends IDao<IEmployee, Integer>{
	
	List<IEmployee> find(EmployeeFilter filter);
	
	IEmployee getFullInfo(final Integer id);

	long getCount(EmployeeFilter filter);

	Set<IEmployee> getByOrder(Integer id);


}
