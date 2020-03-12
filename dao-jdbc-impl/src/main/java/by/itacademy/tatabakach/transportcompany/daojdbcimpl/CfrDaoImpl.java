package by.itacademy.tatabakach.transportcompany.daojdbcimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.daoapi.ICfrDao;
import by.itacademy.tatabakach.transportcompany.daoapi.ICompanyDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICfr;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CfrFilter;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Cfr;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Company;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.PreparedStatementAction;

@Repository
public class CfrDaoImpl extends AbstractDaoImpl<ICfr, Integer> implements ICfrDao {

	@Autowired
	private ICompanyDao companyDao;

	@Override
	public ICfr createEntity() {
		return new Cfr();
	}

	@Override
	public void insert(final ICfr entity) {
		executeStatement(new PreparedStatementAction<ICfr>(
				String.format("insert into %s (company_id, year) values(?,?)", getTableName()), true) {
			@Override
			public ICfr doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getCompany().getId());
				pStmt.setInt(2, entity.getYear());

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
	public void update(final ICfr entity) {
		throw new RuntimeException("will be implemented in ORM layer");
	}

	@Override
	protected ICfr parseRow(final ResultSet resultSet) throws SQLException {
		final ICfr entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));

		final ICompany company = new Company();
		company.setId((Integer) resultSet.getObject("company_id"));
		entity.setCompany(company);

		entity.setYear(resultSet.getInt("year"));

		return entity;
	}

	@Override
	public List<ICfr> find(final CfrFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final CfrFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public ICfr getFullInfo(final Integer id) {
		final ICfr cfr = get(id);

		if (cfr.getCompany() != null) {
			cfr.setCompany(companyDao.get(cfr.getCompany().getId()));
		}

		return cfr;
	}

	@Override
	protected String getTableName() {
		return "cfr";
	}

}
