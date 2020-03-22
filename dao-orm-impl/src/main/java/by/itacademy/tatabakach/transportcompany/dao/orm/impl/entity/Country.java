package by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICountry;

@Entity
public class Country extends BaseEntity implements ICountry {
	
	@Column
	private String name;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

}
