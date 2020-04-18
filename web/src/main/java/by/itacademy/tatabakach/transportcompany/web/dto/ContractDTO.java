package by.itacademy.tatabakach.transportcompany.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class ContractDTO {

	private Integer id;
	
	private String number;
	
	@NotNull
	private Integer ourCompanyId;
	@Size(min = 1, max = 250)
	private String ourCompanyName;

	@NotNull
	private Integer companyId;
	@Size(min = 1, max = 250)
	private String companyName;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getOurCompanyId() {
		return ourCompanyId;
	}

	public void setOurCompanyId(Integer ourCompanyId) {
		this.ourCompanyId = ourCompanyId;
	}

	public String getOurCompanyName() {
		return ourCompanyName;
	}

	public void setOurCompanyName(String ourCompanyName) {
		this.ourCompanyName = ourCompanyName;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
