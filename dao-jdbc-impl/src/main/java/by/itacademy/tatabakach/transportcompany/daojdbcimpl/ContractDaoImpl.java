package by.itacademy.tatabakach.transportcompany.daojdbcimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.daoapi.ICompanyDao;
import by.itacademy.tatabakach.transportcompany.daoapi.IContractDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IContract;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.ContractFilter;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Company;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Contract;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.PreparedStatementAction;

@Repository
public class ContractDaoImpl extends AbstractDaoImpl<IContract, Integer> implements IContractDao {

	@Autowired
	private ICompanyDao companyDao;

	@Override
	public IContract createEntity() {
		return new Contract();
	}

	@Override
	public void insert(final IContract entity) {
		executeStatement(new PreparedStatementAction<IContract>(String.format(
				"insert into %s (number, our_company_id, company_id, date) values(?,?,?,?)", getTableName()), true) {
			@Override
			public IContract doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getNumber());
				pStmt.setInt(2, entity.getOurCompany().getId());
				pStmt.setInt(3, entity.getCompany().getId());
				pStmt.setObject(4, entity.getDate(), Types.TIMESTAMP);

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
	public void update(final IContract entity) {
		throw new RuntimeException("will be implemented in ORM layer");
	}

	@Override
	protected IContract parseRow(final ResultSet resultSet) throws SQLException {
		final IContract entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setNumber(resultSet.getString("number"));

		final ICompany ourCompany = new Company();
		ourCompany.setId((Integer) resultSet.getObject("our_company_id"));
		entity.setOurCompany(ourCompany);
		final ICompany company = new Company();
		company.setId((Integer) resultSet.getObject("company_id"));
		entity.setCompany(company);

		entity.setDate(resultSet.getTimestamp("date"));

		return entity;
	}

	@Override
	public List<IContract> find(final ContractFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final ContractFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public IContract getFullInfo(final Integer id) {
		final IContract contract = get(id);

		if (contract.getOurCompany() != null) {
			contract.setOurCompany(companyDao.get(contract.getOurCompany().getId()));
		}
		if (contract.getCompany() != null) {
			contract.setCompany(companyDao.get(contract.getCompany().getId()));
		}

		return contract;
	}

	@Override
	protected String getTableName() {
		return "contract";
	}

}
