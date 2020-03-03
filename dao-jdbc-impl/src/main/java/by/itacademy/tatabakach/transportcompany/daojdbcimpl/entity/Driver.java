package by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDriver;

public class Driver extends BaseEntity implements IDriver {

	private String firstName;
	private String middleName;
	private String lastName;
	private String passportData;
	private String phone;

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getMiddleName() {
		return middleName;
	}

	@Override
	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getPassportData() {
		return passportData;
	}

	@Override
	public void setPassportData(final String passportData) {
		this.passportData = passportData;
	}

	@Override
	public String getPhone() {
		return phone;
	}

	@Override
	public void setPhone(final String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Driver [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", getId()=" + getId() + "]";
	}

}
