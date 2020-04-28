package by.itacademy.tatabakach.transportcompany.web.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.Currency;

public class PaymentDTO {

	private Integer id;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	@NotNull
	private Integer orderId;
	@Size(min = 1, max = 250)
	private String orderName;
	
	@NotNull
	private Integer companyId;
	@Size(min = 1, max = 250)
	private String companyName;
	
	@NotNull
	private Currency currency;
	
	private BigDecimal rate;
	
	@NotNull
	private BigDecimal amount;
	
	private String note;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
}
