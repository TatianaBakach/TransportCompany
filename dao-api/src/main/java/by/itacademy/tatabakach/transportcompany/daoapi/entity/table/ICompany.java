package by.itacademy.tatabakach.transportcompany.daoapi.entity.table;

import java.util.Set;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.CompanyType;

public interface ICompany extends IBaseEntity {
	
	CompanyType getCompanyType();
	
	void setCompanyType(CompanyType companyType);
	
	String getName();

	void setName(String name);

	String getPayerRegistrationNumber ();
	
	void setPayerRegistrationNumber(String payerRegistrationNumber);
	
	IAddress getLegalAddress ();

	void setLegalAddress(IAddress legalAddress);
	
	IAddress getPostAddress ();

	void setPostAddress(IAddress postAddress);
	
	String getBankData ();

	void setBankData(String bankData);
	
	String getEMail ();

	void setEMail(String eMail);
	
	String getPhone ();

	void setPhone(String phone);
	
	IEmployee getCreator();
	
	void setCreator(IEmployee creator);
	
	Set<IEmployee> getEmployees();

	void setEmployees(Set<IEmployee> employees);
}
