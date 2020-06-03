package by.itacademy.tatabakach.transportcompany.web.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


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
	
	@NotNull
	private Integer customerCostId;
	@Size(min = 1, max = 250)
	private String customerCostName;

	@NotNull
	private Boolean paidCustomer;

	@NotNull
	private Integer carrierCostId;
	@Size(min = 1, max = 250)
	private String carrierCostName;

	@NotNull
	private Boolean paidCarrier;
	
	private BigDecimal margin;
	
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
	public Integer getCustomerCostId() {
		return customerCostId;
	}
	public void setCustomerCostId(Integer customerCostId) {
		this.customerCostId = customerCostId;
	}
	public String getCustomerCostName() {
		return customerCostName;
	}
	public void setCustomerCostName(String customerCostName) {
		this.customerCostName = customerCostName;
	}
	public Boolean getPaidCustomer() {
		return paidCustomer;
	}
	public void setPaidCustomer(Boolean paidCustomer) {
		this.paidCustomer = paidCustomer;
	}
	public Integer getCarrierCostId() {
		return carrierCostId;
	}
	public void setCarrierCostId(Integer carrierCostId) {
		this.carrierCostId = carrierCostId;
	}
	public String getCarrierCostName() {
		return carrierCostName;
	}
	public void setCarrierCostName(String carrierCostName) {
		this.carrierCostName = carrierCostName;
	}
	public Boolean getPaidCarrier() {
		return paidCarrier;
	}
	public void setPaidCarrier(Boolean paidCarrier) {
		this.paidCarrier = paidCarrier;
	}
	public BigDecimal getMargin() {
		return margin;
	}
	public void setMargin(BigDecimal margin) {
		this.margin = margin;
	}

}