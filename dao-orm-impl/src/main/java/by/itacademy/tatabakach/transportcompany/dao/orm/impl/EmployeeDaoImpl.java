package by.itacademy.tatabakach.transportcompany.dao.orm.impl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Employee;
import by.itacademy.tatabakach.transportcompany.daoapi.IEmployeeDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.EmployeeFilter;

@Repository
public class EmployeeDaoImpl extends AbstractDaoImpl<IEmployee, Integer> implements IEmployeeDao {

	protected EmployeeDaoImpl() {
		super(Employee.class);
	}

	@Override
	public IEmployee createEntity() {
		return new Employee();
	}

	@Override
	public List<IEmployee> find(EmployeeFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(EmployeeFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IEmployee getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<IEmployee> getByOrder(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
