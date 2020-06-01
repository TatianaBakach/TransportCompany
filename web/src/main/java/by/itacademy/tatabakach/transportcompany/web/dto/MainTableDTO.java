package by.itacademy.tatabakach.transportcompany.web.dto;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.LoadingMethod;

public class MainTableDTO {
	
	private Integer id;
	
	@Size(min = 1, max = 250)
	private String number;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	@NotNull
	private Integer customerId;
	@Size(min = 1, max = 250)
	private String customerName;

	@NotNull
	private Integer carrierId;
	@Size(min = 1, max = 250)
	private String carrierName;
	
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Integer getCarrierId() {
		return carrierId;
	}
	public void setCarrierId(Integer carrierId) {
		this.carrierId = carrierId;
	}
	public String getCarrierName() {
		return carrierName;
	}
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

}