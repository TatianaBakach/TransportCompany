package by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDistrict;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRegion;

public class District extends BaseEntity implements IDistrict {
	
	private String name;

	private IRegion region;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public IRegion getRegion() {
		return region;
	}

	@Override
	public void setRegion(final IRegion region) {
		this.region = region;
	}

	@Override
	public String toString() {
		return "District [name=" + name + "getRegion()=" + getRegion() + ", getId()=" + getId() + "]";
	}

}
