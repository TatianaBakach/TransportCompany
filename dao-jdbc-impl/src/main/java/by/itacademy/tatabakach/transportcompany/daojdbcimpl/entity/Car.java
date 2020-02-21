package by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICar;

public class Car extends BaseEntity implements ICar {

	private String model;
	private String number;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Car [model=" + model + ", number=" + number + ", getModel()=" + getModel() + ", getNumber()="
				+ getNumber() + ", getId()=" + getId() + "]";
	}

}
