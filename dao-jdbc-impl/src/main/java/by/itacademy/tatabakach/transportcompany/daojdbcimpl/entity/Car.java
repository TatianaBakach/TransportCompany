package by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICar;

public class Car extends BaseEntity implements ICar {

	private String model;
	private String number;

	@Override
	public String getModel() {
		return model;
	}

	@Override
	public void setModel(final String model) {
		this.model = model;
	}

	@Override
	public String getNumber() {
		return number;
	}

	@Override
	public void setNumber(final String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Car [model=" + model + ", number=" + number + ", getId()=" + getId() + "]";
	}

}
