package by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.Department;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.Position;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.Role;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;

@Entity
public class Employee extends BaseEntity implements IEmployee {

	@Column
	private String firstName;

	@Column
	private String middleName;

	@Column
	private String lastName;

	@Column
	@Enumerated(EnumType.ORDINAL)
	private Department department;

	@Column
	@Enumerated(EnumType.ORDINAL)
	private Position position;

	@Column
	private String mail;

	@Column
	private String phone;
	
	@Column
	@Enumerated(EnumType.ORDINAL)
	private Role role;

	@Column
	private String password;

	@Column
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
	public String getMail() {
		return mail;
	}

	@Override
	public void setMail(final String mail) {
		this.mail = mail;
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
	public Role getRole() {
		return role;
	}

	@Override
	public void setRole(final Role role) {
		this.role = role;
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
				+ ", department=" + department + ", position=" + position + "e-mail=" + mail + "phone="
				+ phone + "password=" + password + "salary=" + salary + ", getId()=" + getId() + "]";
	}

}
