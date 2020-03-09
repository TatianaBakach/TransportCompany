package by.itacademy.tatabakach.transportcompany.daojdbcimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.daoapi.IAddressDao;
import by.itacademy.tatabakach.transportcompany.daoapi.ICompanyDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.CompanyType;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IAddress;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CompanyFilter;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Address;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Company;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.PreparedStatementAction;

@Repository
public class CompanyDaoImpl extends AbstractDaoImpl<ICompany, Integer> implements ICompanyDao {
	
	@Autowired
	private IAddressDao addressDao;

	@Override
	public ICompany createEntity() {
		return new Company();
	}

	@Override
	public void insert(final ICompany entity) {
		executeStatement(new PreparedStatementAction<ICompany>(String.format(
				"insert into %s (company_type_id, name, payer_registration_number, legal_address_id, post_address_id, bank_data, e_mail, phone) values(?,?,?,?,?,?,?,?)",
				getTableName()), true) {
			@Override
			public ICompany doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getCompanyType().ordinal());
				pStmt.setString(2, entity.getName());
				pStmt.setString(3, entity.getPayerRegistrationNumber());
				pStmt.setInt(4, entity.getAddress().getId());
				pStmt.setInt(5, entity.getAddress().getId());
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
		entity.setCompanyType(CompanyType.values()[(resultSet.getInt("company_type_id"))]);
		entity.setName(resultSet.getString("name"));
		entity.setPayerRegistrationNumber(resultSet.getString("payer_registration_number"));
		
		final IAddress address = new Address();
		address.setId((Integer) resultSet.getObject("legal_address_id"));
		entity.setAddress(address);
		address.setId((Integer) resultSet.getObject("post_address_id"));
		entity.setAddress(address);
		
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
	public ICompany getFullInfo(final Integer id) {
		final ICompany company = get(id);
		
		// как получить company_type?

		if (company.getAddress() != null) {
			company.setAddress(addressDao.get(company.getAddress().getId()));
		}
		
		return company;
	}

	@Override
	protected String getTableName() {
		return "company";
	}

}
