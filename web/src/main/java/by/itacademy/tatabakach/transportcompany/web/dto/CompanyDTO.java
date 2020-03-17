package by.itacademy.tatabakach.transportcompany.web.dto;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.CompanyType;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IAddress;

public class CompanyDTO {
	
	private Integer id;
	private CompanyType companyType;
	private String name;
	private String payerRegistrationNumber;
	private IAddress legalAddress;
	private IAddress postAddress;
	private String bankData;
	private String eMail;
	private String phone;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public CompanyType getCompanyType() {
		return companyType;
	}
	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPayerRegistrationNumber() {
		return payerRegistrationNumber;
	}
	public void setPayerRegistrationNumber(String payerRegistrationNumber) {
		this.payerRegistrationNumber = payerRegistrationNumber;
	}
	public IAddress getLegalAddress() {
		return legalAddress;
	}
	public void setLegalAddress(IAddress legalAddress) {
		this.legalAddress = legalAddress;
	}
	public IAddress getPostAddress() {
		return postAddress;
	}
	public void setPostAddress(IAddress postAddress) {
		this.postAddress = postAddress;
	}
	public String getBankData() {
		return bankData;
	}
	public void setBankData(String bankData) {
		this.bankData = bankData;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
