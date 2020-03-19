package by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICountry;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRegion;

public class Region extends BaseEntity implements IRegion {

	private String name;

	private ICountry country;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public ICountry getCountry() {
		return country;
	}

	@Override
	public void setCountry(final ICountry country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Region [name=" + name + "country=" + country + ", getId()=" + getId() + "]";
	}

}
