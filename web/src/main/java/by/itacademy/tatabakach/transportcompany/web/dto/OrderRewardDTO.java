package by.itacademy.tatabakach.transportcompany.web.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.RewardType;

public class OrderRewardDTO {

	private Integer id;
	
	@NotNull
	private Integer orderId;
	@Size(min = 1, max = 250)
	private String orderName;
	
	@NotNull
	private Integer employeeId;
	@Size(min = 1, max = 250)
	private String employeeName;

	@NotNull
	private RewardType rewardType;

	@NotNull
	private Integer orderRewardPercentId;
	@Size(min = 1, max = 250)
	private String orderRewardPercentName;

	private BigDecimal additionalExpenses;

	private BigDecimal amount;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date paymentDate;

	@NotNull
	private Boolean rewardIssued;

	private String note;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public RewardType getRewardType() {
		return rewardType;
	}

	public void setRewardType(RewardType rewardType) {
		this.rewardType = rewardType;
	}

	public Integer getOrderRewardPercentId() {
		return orderRewardPercentId;
	}

	public void setOrderRewardPercentId(Integer orderRewardPercentId) {
		this.orderRewardPercentId = orderRewardPercentId;
	}

	public String getOrderRewardPercentName() {
		return orderRewardPercentName;
	}

	public void setOrderRewardPercentName(String orderRewardPercentName) {
		this.orderRewardPercentName = orderRewardPercentName;
	}

	public BigDecimal getAdditionalExpenses() {
		return additionalExpenses;
	}

	public void setAdditionalExpenses(BigDecimal additionalExpenses) {
		this.additionalExpenses = additionalExpenses;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Boolean getRewardIssued() {
		return rewardIssued;
	}

	public void setRewardIssued(Boolean rewardIssued) {
		this.rewardIssued = rewardIssued;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
