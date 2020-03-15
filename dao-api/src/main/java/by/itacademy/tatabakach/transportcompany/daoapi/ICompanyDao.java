package by.itacademy.tatabakach.transportcompany.daoapi;

import java.util.List;
import java.util.Set;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CompanyFilter;

public interface ICompanyDao extends IDao<ICompany, Integer> {
	
	Set<ICompany> getByEmployee(Integer id);

	List<ICompany> find(CompanyFilter filter);

	long getCount(CompanyFilter filter);

	ICompany getFullInfo(Integer id);

}
