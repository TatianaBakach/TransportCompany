package by.itacademy.tatabakach.transportcompany.daojdbcimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.ICompanyDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.CompanyType;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CompanyFilter;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Company;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.PreparedStatementAction;

public class CompanyDaoImpl extends AbstractDaoImpl<ICompany, Integer> implements ICompanyDao {

	@Override
	public ICompany createEntity() {
		return new Company();
	}

	@Override
	public void insert(final ICompany entity) {
		executeStatement(new PreparedStatementAction<ICompany>(String.format(
				"insert into %s (companyType, name, payerRegistrationNumber, legalAddress, postAddress, bankData, eMail, phone) values(?,?,?,?,?,?,?,?)",
				getTableName()), true) {
			@Override
			public ICompany doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getCompanyType().name());
				pStmt.setString(2, entity.getName());
				pStmt.setString(3, entity.getPayerRegistrationNumber());
				pStmt.setString(4, entity.getLegalAddress());
				pStmt.setString(5, entity.getPostAddress());
				pStmt.setString(6, entity.getBankData());
				pStmt.setString(7, entity.getEMail());
				pStmt.setString(8, entity.getPhone());

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
	public void update(final ICompany entity) {
		executeStatement(new PreparedStatementAction<ICompany>(String.format(
				"update %s set companyType=?, name=?, payerRegistrationNumber=?, legalAddress=?, postAddress=?, bankData=?, eMail=?, phone=? where id=?",
				getTableName())) {
			@Override
			public ICompany doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getCompanyType().name());
				pStmt.setString(2, entity.getName());
				pStmt.setString(3, entity.getPayerRegistrationNumber());
				pStmt.setString(4, entity.getLegalAddress());
				pStmt.setString(5, entity.getPostAddress());
				pStmt.setString(6, entity.getBankData());
				pStmt.setString(7, entity.getEMail());
				pStmt.setString(8, entity.getPhone());
				pStmt.setInt(9, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	protected ICompany parseRow(final ResultSet resultSet) throws SQLException {
		final ICompany entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setCompanyType(CompanyType.valueOf(resultSet.getString("companyType")));
		entity.setName(resultSet.getString("name"));
		entity.setPayerRegistrationNumber(resultSet.getString("payerRegistrationNumber"));
		entity.setLegalAddress(resultSet.getString("legalAddress"));
		entity.setPostAddress(resultSet.getString("postAddress"));
		entity.setBankData(resultSet.getString("bankData"));
		entity.setEMail(resultSet.getString("eMail"));
		entity.setPhone(resultSet.getString("phone"));

		return entity;
	}

	@Override
	public List<ICompany> find(final CompanyFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final CompanyFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	protected String getTableName() {
		return "company";
	}

}
