package by.itacademy.tatabakach.transportcompany.daoapi.entity.table;

public interface IDriver extends IBaseEntity {
	
	String getFirstName();

	void setFirstName(String firstName);
	
	String getMiddleName();

	void setMiddleName(String middleName);
	
	String getLastName();

	void setLastName(String lastName);

	String getPassportData();

	void setPassportData(String passportData);
	
	String getPhone();

	void setPhone(String phone);



}
