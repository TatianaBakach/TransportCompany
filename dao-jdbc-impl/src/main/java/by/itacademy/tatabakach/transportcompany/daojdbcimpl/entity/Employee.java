package by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity;

import java.math.BigDecimal;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.Department;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.Position;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;

public class Employee extends BaseEntity implements IEmployee {

	private String firstName;

	private String middleName;

	private String lastName;

	private Department department;

	private Position position;

	private String eMail;

	private String phone;

	private String login;

	private String password;

	private BigDecimal salary;

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
	public Department getDepartment() {
		return department;
	}

	@Override
	public void setDepartment(final Department department) {
		this.department = department;
	}

	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public void setPosition(final Position position) {
		this.position = position;
	}

	@Override
	public String getEMail() {
		return eMail;
	}

	@Override
	public void setEMail(final String eMail) {
		this.eMail = eMail;
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
	public String getLogin() {
		return login;
	}

	@Override
	public void setLogin(final String login) {
		this.login = login;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(final String password) {
		this.password = password;
	}

	@Override
	public BigDecimal getSalary() {
		return salary;
	}

	@Override
	public void setSalary(final BigDecimal salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
				+ ", department=" + department + ", position=" + position + "e-mail=" + eMail + "phone="
				+ phone + "login=" + login + "password=" + password + "salary=" + salary + ", getId()=" + getId() + "]";
	}

}
