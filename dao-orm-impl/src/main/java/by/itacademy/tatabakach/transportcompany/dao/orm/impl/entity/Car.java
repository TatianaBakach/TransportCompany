package by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICar;

@Entity
@Indexed
public class Car extends BaseEntity implements ICar {

	@Column // simple column
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
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
