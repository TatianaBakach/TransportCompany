package by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.CompanyType;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IAddress;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;

@Entity
public class Company extends BaseEntity implements ICompany {

	@Column
	@Enumerated(EnumType.ORDINAL)
	private CompanyType companyType;

	@Column
	private String name;

	@Column
	private String payerRegistrationNumber;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Address.class)
	private IAddress legalAddress;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Address.class)
	private IAddress postAddress;
	
	@Column
	private String bankData;

	@Column
	private String eMail;

	@Column
	private String phone;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Employee.class)
	private IEmployee creator;

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
	public IEmployee getCreator() {
		return creator;
	}

	@Override
	public void setCreator(final IEmployee creator) {
		this.creator = creator;
	}

	@Override
	public String toString() {
		return "Company [name=" + name + ", companyType=" + companyType + "payerRegistrationNumber="
				+ payerRegistrationNumber + "legalAddress=" + legalAddress + "postAddress=" + postAddress + "bankData="
				+ bankData + "e-mail=" + eMail + "phone=" + phone + "creator=" + creator +  ", getId()=" + getId() + "]";
	}

}
