package by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.CompanyType;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IAddress;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;

public class Company extends BaseEntity implements ICompany {

	private CompanyType companyType;

	private String name;

	private String payerRegistrationNumber;

	private IAddress legalAddress;

	private IAddress postAddress;

	private String bankData;

	private String eMail;

	private String phone;

	@Override
	public CompanyType getCompanyType() {
		return companyType;
	}

	@Override
	public void setCompanyType(final CompanyType companyType) {
		this.companyType = companyType;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String getPayerRegistrationNumber() {
		return payerRegistrationNumber;
	}

	@Override
	public void setPayerRegistrationNumber(final String payerRegistrationNumber) {
		this.payerRegistrationNumber = payerRegistrationNumber;
	}

	@Override
	public IAddress getLegalAddress() {
		return legalAddress;
	}

	@Override
	public void setLegalAddress(final IAddress legalAddress) {
		this.legalAddress = legalAddress;
	}

	@Override
	public IAddress getPostAddress() {
		return postAddress;
	}

	@Override
	public void setPostAddress(final IAddress postAddress) {
		this.postAddress = postAddress;
	}

	@Override
	public String getBankData() {
		return bankData;
	}

	@Override
	public void setBankData(final String bankData) {
		this.bankData = bankData;
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
	public String toString() {
		return "Company [name=" + name + ", companyType=" + companyType + ", getId()=" + getId() + "]";
	}

}
