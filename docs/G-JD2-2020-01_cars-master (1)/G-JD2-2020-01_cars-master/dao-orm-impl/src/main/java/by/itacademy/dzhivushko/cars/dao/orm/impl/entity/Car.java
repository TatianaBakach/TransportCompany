package by.itacademy.dzhivushko.cars.dao.orm.impl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import by.itacademy.dzhivushko.cars.dao.api.entity.table.ICar;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IModel;

@Entity
public class Car extends BaseEntity implements ICar {

	@Column
	private String vin;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Model.class)
	private IModel model;

	@Column
	private Boolean sold;

	@Column
	private Date soldDate;

	@Override
	public String getVin() {
		return vin;
	}

	@Override
	public void setVin(final String vin) {
		this.vin = vin;
	}

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public void setModel(final IModel model) {
		this.model = model;
	}

	@Override
	public Boolean getSold() {
		return sold;
	}

	@Override
	public void setSold(final Boolean sold) {
		this.sold = sold;
	}

	@Override
	public Date getSoldDate() {
		return soldDate;
	}

	@Override
	public void setSoldDate(final Date soldDate) {
		this.soldDate = soldDate;
	}

}
