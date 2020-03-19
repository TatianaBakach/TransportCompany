package by.itacademy.tatabakach.transportcompany.daoapi.entity.table;

import java.util.Date;

public interface IRouteItem extends IBaseEntity {
	
	IOrder getOrder();

	void setOrder(IOrder order);
	
	IAddress getAddress ();

	void setAddress(IAddress address);
	
	Date getDate();

	void setDate(Date date);
	
	String getCargoWeight();

	void setCargoWeight(String cargoWeight);
	
	String getCargoVolume();

	void setCargoVolume(String cargoVolume);
	
	IAddress getCustom();

	void setCustom(IAddress custom);
	
	String getContactPerson();

	void setContactPerson(String contactPerson);
	
	String getContactPhone();

	void setContactPhone(String contactPhone);

}
