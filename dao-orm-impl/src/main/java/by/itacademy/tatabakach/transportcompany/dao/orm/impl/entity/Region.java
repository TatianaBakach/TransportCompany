package by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICountry;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRegion;

@Entity
public class Region extends BaseEntity implements IRegion {

	@Column
	private String name;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Country.class)
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
