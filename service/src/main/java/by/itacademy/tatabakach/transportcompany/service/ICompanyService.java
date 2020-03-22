package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CompanyFilter;

public interface ICompanyService {

	ICompany get(Integer id);

	List<ICompany> getAll();

	@Transactional
	void save(ICompany entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	ICompany createEntity();
	
	ICompany getFullInfo(Integer id);

	List<ICompany> find(CompanyFilter filter);

	long getCount(CompanyFilter filter);

}
