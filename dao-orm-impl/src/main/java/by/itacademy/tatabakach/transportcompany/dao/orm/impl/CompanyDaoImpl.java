package by.itacademy.tatabakach.transportcompany.dao.orm.impl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Company;
import by.itacademy.tatabakach.transportcompany.daoapi.ICompanyDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CompanyFilter;

@Repository
public class CompanyDaoImpl extends AbstractDaoImpl<ICompany, Integer> implements ICompanyDao {

	protected CompanyDaoImpl() {
		super(Company.class);
	}

	@Override
	public ICompany createEntity() {
		return new Company();
	}

	@Override
	public Set<ICompany> getByEmployee(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ICompany> find(CompanyFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(CompanyFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ICompany getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
