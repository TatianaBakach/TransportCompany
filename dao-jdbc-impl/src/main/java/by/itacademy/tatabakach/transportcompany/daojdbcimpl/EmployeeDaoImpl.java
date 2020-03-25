package by.itacademy.tatabakach.transportcompany.daojdbcimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.daoapi.IEmployeeDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.Department;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.Position;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.EmployeeFilter;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Employee;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.PreparedStatementAction;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.SQLExecutionException;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.StatementAction;

@Repository
public class EmployeeDaoImpl extends AbstractDaoImpl<IEmployee, Integer> implements IEmployeeDao {

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
				pStmt.setInt(4, entity.getDepartment().ordinal());
				pStmt.setInt(5, entity.getPosition().ordinal());
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

		/*
		 * final IDepartment department = new Department(); department.setId((Integer)
		 * resultSet.getObject("department_id")); entity.setDepartment(department);
		 */
		/*
		 * final Integer departmentId = (Integer) resultSet.getObject("department_id");
		 * if (departmentId != null) { final Department department = new Department();
		 * department.setId(departmentId); entity.setDepartment(department); }
		 * 
		 * /* final IPosition position = new Position(); position.setId((Integer)
		 * resultSet.getObject("position_id")); entity.setPosition(position);
		 */

		/*
		 * final Integer positionId = (Integer) resultSet.getObject("position_id"); if
		 * (positionId != null) { final Position position = new Position();
		 * position.setId(positionId); entity.setPosition(position); }
		 */

		entity.setDepartment(Department.values()[(resultSet.getInt("department_id"))]);
		entity.setPosition(Position.values()[(resultSet.getInt("position_id"))]);
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
	public void deleteAll() {
		try (Connection c = getConnection();
				PreparedStatement deleteCompanyRefsStmt = c.prepareStatement("delete from employee_2_company");
				PreparedStatement deleteAllStmt = c.prepareStatement("delete from " + getTableName());) {
			c.setAutoCommit(false);
			try {
				deleteCompanyRefsStmt.executeUpdate();
				deleteAllStmt.executeUpdate();

				deleteCompanyRefsStmt.close();
				deleteAllStmt.close();

				c.commit();
			} catch (final Exception e) {
				c.rollback();
				throw new RuntimeException(e);
			}

		} catch (final SQLException e) {
			throw new SQLExecutionException(e);
		}

	}

	@Override
	public Set<IEmployee> getByOrder(final Integer id) {
		return executeStatement(new StatementAction<Set<IEmployee>>() {
			@Override
			public Set<IEmployee> doWithStatement(final Statement statement) throws SQLException {
				// @formatter:off
				statement.executeQuery(
						String.format("select * from %s e " + "inner join order_2_employee o2e on e.id=o2e.employee_id "
								+ "where o2e.order_id=%s", getTableName(), id));
				// @formatter:on
				final ResultSet resultSet = statement.getResultSet();

				final Set<IEmployee> result = new HashSet<IEmployee>();
				boolean hasNext = resultSet.next();
				while (hasNext) {
					result.add(parseRow(resultSet));
					hasNext = resultSet.next();
				}
				resultSet.close();

				return result;
			}
		});
	}

	@Override
	public Set<IEmployee> getByCompany(final Integer id) {
		return executeStatement(new StatementAction<Set<IEmployee>>() {
			@Override
			public Set<IEmployee> doWithStatement(final Statement statement) throws SQLException {
				// @formatter:off
				statement.executeQuery(String.format("select * from %s e "
						+ "inner join company_2_employee c2e on e.id=c2e.employee_id " + "where c2e.company_id=%s",
						getTableName(), id));
				// @formatter:on
				final ResultSet resultSet = statement.getResultSet();

				final Set<IEmployee> result = new HashSet<IEmployee>();
				boolean hasNext = resultSet.next();
				while (hasNext) {
					result.add(parseRow(resultSet));
					hasNext = resultSet.next();
				}
				resultSet.close();

				return result;
			}
		});
	}

	@Override
	protected String getTableName() {
		return "employee";
	}

}
