package by.itacademy.tatabakach.transportcompany.daojdbcimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.daoapi.IAddressDao;
import by.itacademy.tatabakach.transportcompany.daoapi.ICompanyDao;
import by.itacademy.tatabakach.transportcompany.daoapi.IEmployeeDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.CompanyType;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IAddress;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CompanyFilter;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Address;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Company;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Employee;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.SQLExecutionException;

@Repository
public class CompanyDaoImpl extends AbstractDaoImpl<ICompany, Integer> implements ICompanyDao {

	@Autowired
	private IAddressDao addressDao;
	
	@Autowired
	private IEmployeeDao employeeDao;

	@Override
	public ICompany createEntity() {
		return new Company();
	}

	@Override
	public void insert(final ICompany entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String
						.format("insert into %s (company_type_id, name, payer_registration_number, legal_address_id, post_address_id, bank_data, e_mail, phone, creator_id) values(?,?,?,?,?,?,?,?,?)", getTableName()),
						Statement.RETURN_GENERATED_KEYS)) {
			c.setAutoCommit(false);
			try {
				pStmt.setInt(1, entity.getCompanyType().ordinal());
				pStmt.setString(2, entity.getName());
				pStmt.setString(3, entity.getPayerRegistrationNumber());
				pStmt.setInt(4, entity.getLegalAddress().getId());
				pStmt.setInt(5, entity.getPostAddress().getId());
				pStmt.setString(6, entity.getBankData());
				pStmt.setString(7, entity.getEMail());
				pStmt.setString(8, entity.getPhone());
				pStmt.setInt(9, entity.getCreator().getId());
				
				pStmt.executeUpdate();

				final ResultSet rs = pStmt.getGeneratedKeys();
				rs.next();
				final int id = rs.getInt("id");

				rs.close();
				entity.setId(id);
				// the same should be done in 'update' DAO method
				updateEmployees(entity, c);
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

		final IAddress legalAddress = new Address();
		legalAddress.setId((Integer) resultSet.getObject("legal_address_id"));
		entity.setLegalAddress(legalAddress);
		final IAddress postAddress = new Address();
		postAddress.setId((Integer) resultSet.getObject("post_address_id"));
		entity.setPostAddress(postAddress);

		entity.setBankData(resultSet.getString("bank_data"));
		entity.setEMail(resultSet.getString("e_mail"));
		entity.setPhone(resultSet.getString("phone"));
		
		final IEmployee creator = new Employee();
		creator.setId((Integer) resultSet.getObject("creator_id"));
		entity.setCreator(creator);
		

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
		final Set<IEmployee> employees = employeeDao.getByOrder(id);
		company.setEmployees(employees);;

		if (company.getLegalAddress() != null) {
			company.setLegalAddress(addressDao.get(company.getLegalAddress().getId()));
		}

		if (company.getPostAddress() != null) {
			company.setPostAddress(addressDao.get(company.getPostAddress().getId()));
		}
		
		if(company.getCreator() !=null) {
			company.setCreator(employeeDao.get(company.getCreator().getId()));
		}

		return company;
	}

	@Override
	protected String getTableName() {
		return "company";
	}
	
	private void updateEmployees(final ICompany entity, final Connection c) throws SQLException {
		// clear all existing records related to the current order
		final PreparedStatement deleteStmt = c.prepareStatement("delete from company_2_employee where company_id=?");
		deleteStmt.setInt(1, entity.getId());
		deleteStmt.executeUpdate();
		deleteStmt.close();

		if (entity.getEmployees().isEmpty()) {
			return;
		}

		// insert actual list of records using 'batch'
		final PreparedStatement pStmt = c
				.prepareStatement("insert into company_2_employee (company_id, employee_id) values(?,?)");

		for (final IEmployee e : entity.getEmployees()) {
			pStmt.setInt(1, entity.getId());
			pStmt.setInt(2, e.getId());
			pStmt.addBatch();
		}
		pStmt.executeBatch();
		pStmt.close();
	}
	
	
	@Override
	public void deleteAll() {
		try (Connection c = getConnection();
				PreparedStatement deleteEmployeeRefsStmt = c.prepareStatement("delete from company_2_employee");
				PreparedStatement deleteAllStmt = c.prepareStatement("delete from " + getTableName());) {
			c.setAutoCommit(false);
			try {
				deleteEmployeeRefsStmt.executeUpdate();
				deleteAllStmt.executeUpdate();

				deleteEmployeeRefsStmt.close();
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

}
