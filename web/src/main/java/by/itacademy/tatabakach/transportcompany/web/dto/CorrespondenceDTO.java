package by.itacademy.tatabakach.transportcompany.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.CorrespondenceType;

public class CorrespondenceDTO {

	private Integer id;
	
	@NotNull
	private CorrespondenceType correspondenceType;

	private Integer orderId;
	private String orderName;
	
	@NotNull
	private Integer companyId;
	@Size(min = 1, max = 250)
	private String companyName;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	@Size(min = 1, max = 2500)
	private String content;

	private String note;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CorrespondenceType getCorrespondenceType() {
		return correspondenceType;
	}

	public void setCorrespondenceType(CorrespondenceType correspondenceType) {
		this.correspondenceType = correspondenceType;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
