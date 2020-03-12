package by.itacademy.tatabakach.transportcompany.daojdbcimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.daoapi.IDepartmentDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDepartment;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.DepartmentFilter;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Department;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.PreparedStatementAction;

@Repository
public class DepartmentDaoImpl extends AbstractDaoImpl<IDepartment, Integer> implements IDepartmentDao{
	
	@Override
	public IDepartment createEntity() {
		return new Department();
	}

	@Override
	public void insert(final IDepartment entity) {
		executeStatement(new PreparedStatementAction<IDepartment>(
				String.format("insert into %s (name) values(?)", getTableName()), true) {
			@Override
			public IDepartment doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());

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
	public void update(final IDepartment entity) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public List<IDepartment> find(final DepartmentFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final DepartmentFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	protected IDepartment parseRow(final ResultSet resultSet) throws SQLException {
		final IDepartment entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		return entity;
	}

	@Override
	protected String getTableName() {
		return "department";
	}

}
