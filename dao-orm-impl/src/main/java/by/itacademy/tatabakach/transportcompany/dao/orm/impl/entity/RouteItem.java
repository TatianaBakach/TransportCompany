package by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IAddress;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRouteItem;

@Entity
public class RouteItem extends BaseEntity implements IRouteItem{
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Order.class)
	private IOrder order;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Address.class)
	private IAddress address;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date date;
	
	@Column
	private String 	cargoWeight;
	
	@Column
	private String cargoVolume;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Address.class)
	private IAddress custom;
	
	@Column
	private String contactPerson;
	
	@Column
	private String contactPhone;
	
	@Column
	private String note;

	@Override
	public IOrder getOrder() {
		return order;
	}

	@Override
	public void setOrder(final IOrder order) {
		this.order = order;
	}

	@Override
	public IAddress getAddress() {
		return address;
	}

	@Override
	public void setAddress(final IAddress address) {
		this.address = address;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public void setDate(final Date date) {
		this.date = date;
	}

	@Override
	public String getCargoWeight() {
		return cargoWeight;
	}

	@Override
	public void setCargoWeight(final String cargoWeight) {
		this.cargoWeight = cargoWeight;
	}

	@Override
	public String getCargoVolume() {
		return cargoVolume;
	}

	@Override
	public void setCargoVolume(final String cargoVolume) {
		this.cargoVolume = cargoVolume;
	}

	@Override
	public IAddress getCustom() {
		return custom;
	}

	@Override
	public void setCustom(final IAddress custom) {
		this.custom = custom;
	}

	@Override
	public String getContactPerson() {
		return contactPerson;
	}

	@Override
	public void setContactPerson(final String contactPerson) {
		this.contactPerson = contactPerson;
	}

	@Override
	public String getContactPhone() {
		return contactPhone;
	}

	@Override
	public void setContactPhone(final String contactPhone) {
		this.contactPhone = contactPhone;
	}
	
	@Override
	public String getNote() {
		return note;
	}

	@Override
	public void setNote(final String note) {
		this.note = note;
	}
	
}
