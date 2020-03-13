package by.itacademy.tatabakach.transportcompany.web.dto;

public class DriverDTO {
	
	private Integer id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String passportData;
	private String phone;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassportData() {
		return passportData;
	}

	public void setPassportData(String passportData) {
		this.passportData = passportData;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/*@Override
	public String toString() {
		return "Car [model=" + model + ", number=" + number + ", getId()=" + getId() + "]";
	}*/


}
