package by.itacademy.tatabakach.transportcompany.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Country;
import by.itacademy.tatabakach.transportcompany.daoapi.ICountryDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICountry;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CountryFilter;

@Repository
public class CountryDaoImpl extends AbstractDaoImpl<ICountry, Integer> implements ICountryDao {
	
	protected CountryDaoImpl() {
		super(Country.class);
	}

	@Override
	public ICountry createEntity() {
		return new Country();
	}

	@Override
	public List<ICountry> find(CountryFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(CountryFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

}
