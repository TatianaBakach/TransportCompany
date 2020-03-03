package by.itacademy.tatabakach.transportcompany.daoapi.entity.table;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.CompanyType;

public interface ICompany extends IBaseEntity {
	
	CompanyType getCompanyType();
	
	void setCompanyType(CompanyType companyType);
	
	String getName();

	void setName(String name);

	String getPayerRegistrationNumber ();

	void setPayerRegistrationNumber(String payerRegistrationNumber);
	
	String getLegalAddress ();

	void setLegalAddress(String legalAddress);
	
	String getPostAddress ();

	void setPostAddress(String postAddress);
	
	String getBankData ();

	void setBankData(String bankData);
	
	String getEMail ();

	void setEMail(String eMail);
	
	String getPhone ();

	void setPhone(String phone);
}
