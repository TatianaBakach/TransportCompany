package by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ILocality;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRegion;

public class Locality extends BaseEntity implements ILocality{
	
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
		return "Locality [name=" + name + "region=" + region + ", getId()=" + getId() + "]";
	}

}
