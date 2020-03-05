package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICountry;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CountryFilter;

public interface ICountryService {
	
	ICountry get(Integer id);

	List<ICountry> getAll();

	void save(ICountry entity);

	void delete(Integer id);

	void deleteAll();

	ICountry createEntity();
	
	//List<ICountry> find(CountryFilter filter);

	//long getCount(CountryFilter filter);

}
