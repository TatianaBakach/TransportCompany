package by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICar;

@Entity
public class Car extends BaseEntity implements ICar {

	@Column // simple column
	private String model;
	
	@Column
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
