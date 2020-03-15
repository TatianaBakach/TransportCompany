package by.itacademy.tatabakach.transportcompany.daojdbcimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.daoapi.ICompanyDao;
import by.itacademy.tatabakach.transportcompany.daoapi.IDepartmentDao;
import by.itacademy.tatabakach.transportcompany.daoapi.IEmployeeDao;
import by.itacademy.tatabakach.transportcompany.daoapi.IPositionDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.EmployeeFilter;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Department;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Employee;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Position;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.SQLExecutionException;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.StatementAction;

@Repository
public class EmployeeDaoImpl extends AbstractDaoImpl<IEmployee, Integer> implements IEmployeeDao {

	@Autowired
	private IDepartmentDao departmentDao;

	@Autowired
	private IPositionDao positionDao;

	@Autowired
	private ICompanyDao companyDao;

	@Override
	public IEmployee createEntity() {
		return new Employee();
	}

	@Override
	public void insert(final IEmployee entity) {

		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String.format(
						"insert into %s (first_name, middle_name, last_name, department_id, position_id, e_mail, phone, login, password, salary) values(?,?,?,?,?,?,?,?,?,?)",
						getTableName()), Statement.RETURN_GENERATED_KEYS)) {
			c.setAutoCommit(false);
			try {
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
				// the same should be done in 'update' DAO method
				updateCompanies(entity, c);
				c.commit();
			} catch (final Exception e) {
				c.rollback();
				throw new RuntimeException(e);
			}

		} catch (final SQLException e) {
			throw new SQLExecutionException(e);
		}
		/*
		 * executeStatement(new PreparedStatementAction<IEmployee>(String.format(
		 * "insert into %s (first_name, middle_name, last_name, department_id, position_id, e_mail, phone, login, password, salary) values(?,?,?,?,?,?,?,?,?,?)"
		 * , getTableName()), true) {
		 * 
		 * @Override public IEmployee doWithPreparedStatement(final PreparedStatement
		 * pStmt) throws SQLException { pStmt.setString(1, entity.getFirstName());
		 * pStmt.setString(2, entity.getMiddleName()); pStmt.setString(3,
		 * entity.getLastName()); pStmt.setInt(4, entity.getDepartment().getId());
		 * pStmt.setInt(5, entity.getPosition().getId()); pStmt.setString(6,
		 * entity.getEMail()); pStmt.setString(7, entity.getPhone()); pStmt.setString(8,
		 * entity.getLogin()); pStmt.setString(9, entity.getPassword());
		 * pStmt.setBigDecimal(10, entity.getSalary());
		 * 
		 * pStmt.executeUpdate();
		 * 
		 * final ResultSet rs = pStmt.getGeneratedKeys(); rs.next(); final int id =
		 * rs.getInt("id");
		 * 
		 * rs.close();
		 * 
		 * entity.setId(id); return entity; } });
		 */
	}

	@Override
	public void update(final IEmployee entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(
						String.format("update %s set first_name=?, middle_name=?, last_name=?, department_id=?, position_id=?, e_mail=?, phone=?, login=?, password=?, salary=? where id=?", getTableName()))) {
			c.setAutoCommit(false);
			try {
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
				pStmt.setInt(11, entity.getId());
				pStmt.executeUpdate();
				// the same should be done in 'update' DAO method
				updateCompanies(entity, c);
				c.commit();
			} catch (final Exception e) {
				c.rollback();
				throw new RuntimeException(e);
			}

		} catch (final SQLException e) {
			throw new SQLExecutionException(e);
		}
		
		//throw new RuntimeException("will be implemented in ORM layer");
	}

	@Override
	protected IEmployee parseRow(final ResultSet resultSet, final Set<String> columns) throws SQLException {

		final IEmployee entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setFirstName(resultSet.getString("first_name"));
		entity.setMiddleName(resultSet.getString("middle_name"));
		entity.setLastName(resultSet.getString("last_name"));

		/*
		 * final IDepartment department = new Department(); department.setId((Integer)
		 * resultSet.getObject("department_id")); entity.setDepartment(department);
		 */

		final Integer departmentId = (Integer) resultSet.getObject("department_id");
		if (departmentId != null) {
			final Department department = new Department();
			department.setId(departmentId);
			entity.setDepartment(department);
		}

		/*
		 * final IPosition position = new Position(); position.setId((Integer)
		 * resultSet.getObject("position_id")); entity.setPosition(position);
		 */

		final Integer positionId = (Integer) resultSet.getObject("position_id");
		if (positionId != null) {
			final Position position = new Position();
			position.setId(positionId);
			entity.setPosition(position);
		}

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
	public IEmployee getFullInfo(final Integer id) {
		final IEmployee employee = get(id);
		final Set<ICompany> companies = companyDao.getByEmployee(id);
		employee.setCompanies(companies);

		if (employee.getDepartment() != null) {
			employee.setDepartment(departmentDao.get(employee.getDepartment().getId()));
		}

		if (employee.getPosition() != null) {
			employee.setPosition(positionDao.get(employee.getPosition().getId()));
		}

		return employee;
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
	protected String getTableName() {
		return "employee";
	}

	private void updateCompanies(final IEmployee entity, final Connection c) throws SQLException {
		// clear all existing records related to the current employee
		final PreparedStatement deleteStmt = c.prepareStatement("delete from employee_2_company where employee_id=?");
		deleteStmt.setInt(1, entity.getId());
		deleteStmt.executeUpdate();
		deleteStmt.close();

		if (entity.getCompanies().isEmpty()) {
			return;
		}

		// insert actual list of records using 'batch'
		final PreparedStatement pStmt = c
				.prepareStatement("insert into employee_2_company (employee_id, company_id) values(?,?)");

		for (final ICompany e : entity.getCompanies()) {
			pStmt.setInt(1, entity.getId());
			pStmt.setInt(2, e.getId());
			

			pStmt.addBatch();
		}
		pStmt.executeBatch();
		pStmt.close();
	}

}
