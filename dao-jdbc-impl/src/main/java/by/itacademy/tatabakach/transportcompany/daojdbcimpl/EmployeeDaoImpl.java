package by.itacademy.tatabakach.transportcompany.daojdbcimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.daoapi.IDepartmentDao;
import by.itacademy.tatabakach.transportcompany.daoapi.IEmployeeDao;
import by.itacademy.tatabakach.transportcompany.daoapi.IPositionDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDepartment;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IPosition;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.EmployeeFilter;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Department;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Employee;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Position;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.PreparedStatementAction;

@Repository
public class EmployeeDaoImpl extends AbstractDaoImpl<IEmployee, Integer> implements IEmployeeDao {

	@Autowired
	private IDepartmentDao departmentDao;
	
	@Autowired
	private IPositionDao positionDao;

	@Override
	public IEmployee createEntity() {
		return new Employee();
	}

	@Override
	public void insert(final IEmployee entity) {
		executeStatement(new PreparedStatementAction<IEmployee>(String.format(
				"insert into %s (first_name, middle_name, last_name, department_id, position_id, e_mail, phone, login, password, salary) values(?,?,?,?,?,?,?,?,?,?)",
				getTableName()), true) {
			@Override
			public IEmployee doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getFirstName());
				pStmt.setString(2, entity.getMiddleName());
				pStmt.setString(3, entity.getLastName());
				pStmt.setInt(4, entity.getDepartment().getId());
				pStmt.setInt(5, entity.getPosition().getId());
				pStmt.setString(6, entity.getEMail());
				pStmt.setString(7, entity.getPhone());
				pStmt.setString(8, entity.getLogin());
				pStmt.setString(9, entity.getPassword());
				pStmt.setBigDecimal(10, entity.getSalary());

				pStmt.executeUpdate();

				final ResultSet rs = pStmt.getGeneratedKeys();
				rs.next();
				final int id = rs.getInt("id");

				rs.close();

				entity.setId(id);
				return entity;
			}
		});
	}

	@Override
	public void update(final IEmployee entity) {
		throw new RuntimeException("will be implemented in ORM layer");
	}

	@Override
	protected IEmployee parseRow(final ResultSet resultSet) throws SQLException {
		final IEmployee entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setFirstName(resultSet.getString("first_name"));
		entity.setMiddleName(resultSet.getString("middle_name"));
		entity.setLastName(resultSet.getString("last_name"));
		
		final IDepartment department = new Department();
		department.setId((Integer) resultSet.getObject("department_id"));
		entity.setDepartment(department);
		
		final IPosition position = new Position();
		position.setId((Integer) resultSet.getObject("position_id"));
		entity.setPosition(position);;

		entity.setEMail(resultSet.getString("e_mail"));
		entity.setPhone(resultSet.getString("phone"));
		entity.setLogin(resultSet.getString("login"));
		entity.setPassword(resultSet.getString("password"));
		entity.setSalary(resultSet.getBigDecimal("salary"));

		return entity;
	}

	@Override
	public List<IEmployee> find(final EmployeeFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final EmployeeFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public IEmployee getFullInfo(final Integer id) {
		final IEmployee employee = get(id);

		if (employee.getDepartment() != null) {
			employee.setDepartment(departmentDao.get(employee.getDepartment().getId()));
		}

		if (employee.getPosition() != null) {
			employee.setPosition(positionDao.get(employee.getPosition().getId()));
		}

		return employee;
	}

	@Override
	protected String getTableName() {
		return "employee";
	}

}
