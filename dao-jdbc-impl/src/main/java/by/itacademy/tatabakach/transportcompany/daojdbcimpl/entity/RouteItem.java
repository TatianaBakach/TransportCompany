package by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity;

import java.util.Date;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IAddress;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRouteItem;

public class RouteItem extends BaseEntity implements IRouteItem{
	
	private IOrder order;
	
	private IAddress address;
	
	private Date date;
	
	private String 	cargoWeight;
	
	private String cargoVolume;
	
	private IAddress custom;
	
	private String contactPerson;
	
	private String contactPhone;
	
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
