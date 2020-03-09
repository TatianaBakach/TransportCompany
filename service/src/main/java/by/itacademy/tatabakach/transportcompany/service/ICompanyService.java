package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CompanyFilter;

public interface ICompanyService {

	ICompany get(Integer id);

	List<ICompany> getAll();

	void save(ICompany entity);

	void delete(Integer id);

	void deleteAll();

	ICompany createEntity();
	
	ICompany getFullInfo(Integer id);

	List<ICompany> find(CompanyFilter filter);

	long getCount(CompanyFilter filter);

}
