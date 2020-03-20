package by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICountry;

public class Country extends BaseEntity implements ICountry {
	
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
