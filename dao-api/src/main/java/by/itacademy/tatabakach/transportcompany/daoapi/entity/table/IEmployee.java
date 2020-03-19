package by.itacademy.tatabakach.transportcompany.daoapi.entity.table;

import java.math.BigDecimal;
import java.util.Set;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.Department;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.Position;

public interface IEmployee extends IBaseEntity {
	
	String getFirstName();

	void setFirstName(String firstName);
	
	String getMiddleName();

	void setMiddleName(String middleName);
	
	String getLastName();

	void setLastName(String lastName);
	
	Department getDepartment();
	
	void setDepartment(Department department);
	
	Position getPosition();
	
	void setPosition(Position position);
	
	String getEMail ();

	void setEMail(String eMail);
	
	String getPhone ();

	void setPhone(String phone);
	
	String getLogin();
	
	void setLogin(String login);
	
	String getPassword();
	
	void setPassword (String password);
	
	BigDecimal getSalary();
	
	void setSalary(BigDecimal salary);
	
	Set<ICompany> getCompanies();

	void setCompanies(Set<ICompany> companies);

}
