package by.itacademy.tatabakach.transportcompany.daojdbcimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.daoapi.ICompanyDao;
import by.itacademy.tatabakach.transportcompany.daoapi.ICorrespondenceDao;
import by.itacademy.tatabakach.transportcompany.daoapi.IOrderDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.CorrespondenceType;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICorrespondence;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CorrespondenceFilter;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Company;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Correspondence;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Order;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.PreparedStatementAction;

@Repository
public class CorrespondenceDaoImpl extends AbstractDaoImpl<ICorrespondence, Integer> implements ICorrespondenceDao {

	@Autowired
	private IOrderDao orderDao;
	
	@Autowired
	private ICompanyDao companyDao;

	@Override
	public ICorrespondence createEntity() {
		return new Correspondence();
	}

	@Override
	public void insert(final ICorrespondence entity) {
		executeStatement(new PreparedStatementAction<ICorrespondence>(String.format(
				"insert into %s (correspondence_type_id, order_id, company_id, date, content, note) values(?,?,?,?,?,?)", getTableName()), true) {
			@Override
			public ICorrespondence doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getCorrespondenceType().ordinal());
				pStmt.setInt(2, entity.getOrder().getId());
				pStmt.setInt(3, entity.getCompany().getId());
				pStmt.setObject(4, entity.getDate(), Types.TIMESTAMP);
				pStmt.setString(5, entity.getContent());
				pStmt.setString(6, entity.getNote());

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
	public void update(final ICorrespondence entity) {
		throw new RuntimeException("will be implemented in ORM layer");
	}

	@Override
	protected ICorrespondence parseRow(final ResultSet resultSet) throws SQLException {
		final ICorrespondence entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setCorrespondenceType(CorrespondenceType.values()[(resultSet.getInt("correspondence_type_id"))]);

		final IOrder order = new Order();
		order.setId((Integer) resultSet.getObject("order_id"));
		entity.setOrder(order);;
		
		final ICompany company = new Company();
		company.setId((Integer) resultSet.getObject("company_id"));
		entity.setCompany(company);

		entity.setDate(resultSet.getTimestamp("date"));
		entity.setContent(resultSet.getString("content"));
		entity.setNote(resultSet.getString("note"));

		return entity;
	}

	@Override
	public List<ICorrespondence> find(final CorrespondenceFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final CorrespondenceFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public ICorrespondence getFullInfo(final Integer id) {
		final ICorrespondence correspondence = get(id);

		if (correspondence.getOrder() != null) {
			correspondence.setOrder(orderDao.get(correspondence.getOrder().getId()));
		}
		
		if (correspondence.getCompany() != null) {
			correspondence.setCompany(companyDao.get(correspondence.getCompany().getId()));
		}
		

		return correspondence;
	}

	@Override
	protected String getTableName() {
		return "correspondence";
	}

}
