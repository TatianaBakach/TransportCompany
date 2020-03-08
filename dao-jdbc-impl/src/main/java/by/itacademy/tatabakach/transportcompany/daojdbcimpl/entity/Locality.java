package by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDistrict;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ILocality;

public class Locality extends BaseEntity implements ILocality{
	
	private String name;

	private IDistrict district;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public IDistrict getDistrict() {
		return district;
	}

	@Override
	public void setDistrict(final IDistrict district) {
		this.district = district;
	}

	@Override
	public String toString() {
		return "Locality [name=" + name + "getDistrict()=" + getDistrict() + ", getId()=" + getId() + "]";
	}

}
