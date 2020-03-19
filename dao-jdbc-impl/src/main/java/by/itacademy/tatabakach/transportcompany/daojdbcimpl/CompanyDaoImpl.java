package by.itacademy.tatabakach.transportcompany.daojdbcimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
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
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.PreparedStatementAction;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.StatementAction;

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
		executeStatement(new PreparedStatementAction<ICompany>(String.format(
				"insert into %s (company_type_id, name, payer_registration_number, legal_address_id, post_address_id, bank_data, e_mail, phone, creator_id) values(?,?,?,?,?,?,?,?,?)",
				getTableName()), true) {
			@Override
			public ICompany doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
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
	public Set<ICompany> getByEmployee(final Integer id) {
		return executeStatement(new StatementAction<Set<ICompany>>() {
			@Override
			public Set<ICompany> doWithStatement(final Statement statement) throws SQLException {
				// @formatter:off
				statement.executeQuery(String.format("select * from %s e "
						+ "inner join employee_2_company e2c on e.id=e2c.company_id " + "where e2c.employee_id=%s",
						getTableName(), id));
				// @formatter:on
				final ResultSet resultSet = statement.getResultSet();

				final Set<ICompany> result = new HashSet<ICompany>();
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
	public List<ICompany> find(final CompanyFilter filter) {
		return selectAll();
	}

	@Override
	public long getCount(final CompanyFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public ICompany getFullInfo(final Integer id) {
		final ICompany company = get(id);

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

}
