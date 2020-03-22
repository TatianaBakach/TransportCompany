package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICountry;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CountryFilter;

public interface ICountryService {
	
	ICountry get(Integer id);

	List<ICountry> getAll();

	@Transactional
	void save(ICountry entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	ICountry createEntity();
	
	List<ICountry> find(CountryFilter filter);

	long getCount(CountryFilter filter);

}
