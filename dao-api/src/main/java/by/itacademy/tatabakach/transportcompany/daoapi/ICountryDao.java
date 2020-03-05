package by.itacademy.tatabakach.transportcompany.daoapi;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICountry;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CountryFilter;

public interface ICountryDao extends IDao<ICountry, Integer>{
	
	List<ICountry> find(CountryFilter filter);

	long getCount(CountryFilter filter);

}
