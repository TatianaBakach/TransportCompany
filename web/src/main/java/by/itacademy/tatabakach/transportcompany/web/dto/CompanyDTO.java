package by.itacademy.tatabakach.transportcompany.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.CompanyType;

public class CompanyDTO {
	
	private Integer id;
	
	@NotNull
	private CompanyType companyType;
	
	@Size(min = 1, max = 250)
	private String name;
	
	private String payerRegistrationNumber;
	
	private Integer legalAddressId;
	private String legalAddressName;
	
	private Integer postAddressId;
	private String postAddressName;
	
	private String bankData;
	
	private String eMail;
	
	private String phone;
	
	@NotNull
	private Integer creatorId;
	@Size(min = 1, max = 250)
	private String creatorName;
	
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
	public Integer getLegalAddressId() {
		return legalAddressId;
	}
	public void setLegalAddressId(Integer legalAddressId) {
		this.legalAddressId = legalAddressId;
	}
	public String getLegalAddressName() {
		return legalAddressName;
	}
	public void setLegalAddressName(String legalAddressName) {
		this.legalAddressName = legalAddressName;
	}
	public Integer getPostAddressId() {
		return postAddressId;
	}
	public void setPostAddressId(Integer postAddressId) {
		this.postAddressId = postAddressId;
	}
	public String getPostAddressName() {
		return postAddressName;
	}
	public void setPostAddressName(String postAddressName) {
		this.postAddressName = postAddressName;
	}
	public String getBankData() {
		return bankData;
	}
	public void setBankData(String bankData) {
		this.bankData = bankData;
	}
	public String getEMail() {
		return eMail;
	}
	public void setEMail(String eMail) {
		this.eMail = eMail;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	
}
