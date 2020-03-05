package by.itacademy.tatabakach.transportcompany.daojdbcimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.daoapi.ICompanyDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.CompanyType;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CompanyFilter;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Company;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.PreparedStatementAction;

@Repository
public class CompanyDaoImpl extends AbstractDaoImpl<ICompany, Integer> implements ICompanyDao {

	@Override
	public ICompany createEntity() {
		return new Company();
	}

	@Override
	public void insert(final ICompany entity) {
		executeStatement(new PreparedStatementAction<ICompany>(String.format(
				"insert into %s (company_type, name, payer_registration_number, legal_address, post_address, bank_data, e_mail, phone) values(?,?,?,?,?,?,?,?)",
				getTableName()), true) {
			@Override
			public ICompany doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getCompanyType().ordinal());
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
		throw new RuntimeException("will be implemented in ORM layer");
	}

	@Override
	protected ICompany parseRow(final ResultSet resultSet) throws SQLException {
		final ICompany entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setCompanyType(CompanyType.values()[(resultSet.getInt("company_type"))]);
		entity.setName(resultSet.getString("name"));
		entity.setPayerRegistrationNumber(resultSet.getString("payer_registration_number"));
		entity.setLegalAddress(resultSet.getString("legal_address"));
		entity.setPostAddress(resultSet.getString("post_address"));
		entity.setBankData(resultSet.getString("bank_data"));
		entity.setEMail(resultSet.getString("e_mail"));
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
