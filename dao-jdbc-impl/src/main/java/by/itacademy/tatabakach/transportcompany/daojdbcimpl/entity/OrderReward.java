package by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity;

import java.math.BigDecimal;
import java.util.Date;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.RewardType;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderReward;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderRewardPercent;

public class OrderReward extends BaseEntity implements IOrderReward {
	
	private IOrder order;
	
	private IEmployee employee;
	
	private RewardType rewardType;
	
	private IOrderRewardPercent orderRewardPercent;
	
	private BigDecimal additionalExpenses;
	
	private BigDecimal amount;
	
	private Date paymentDate;
	
	private Boolean rewardIssued;
	
	private String note;

	@Override
	public IOrder getOrder() {
		return order;
	}

	@Override
	public void setOrder(final IOrder order) {
		this.order = order;
	}

	@Override
	public IEmployee getEmployee() {
		return employee;
	}

	@Override
	public void setEmployee(final IEmployee employee) {
		this.employee = employee;
	}

	@Override
	public RewardType getRewardType() {
		return rewardType;
	}

	@Override
	public void setRewardType(final RewardType rewardType) {
		this.rewardType = rewardType;
	}

	@Override
	public IOrderRewardPercent getOrderRewardPercent() {
		return orderRewardPercent;
	}

	@Override
	public void setOrderRewardPercent(final IOrderRewardPercent orderRewardPercent) {
		this.orderRewardPercent = orderRewardPercent;
	}

	@Override
	public BigDecimal getAdditionalExpenses() {
		return additionalExpenses;
	}

	@Override
	public void setAdditionalExpenses(final BigDecimal additionalExpenses) {
		this.additionalExpenses = additionalExpenses;
	}

	@Override
	public BigDecimal getAmount() {
		return amount;
	}

	@Override
	public void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public Date getPaymentDate() {
		return paymentDate;
	}

	@Override
	public void setPaymentDate(final Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Override
	public Boolean getRewardIssued() {
		return rewardIssued;
	}

	@Override
	public void setRewardIssued(final Boolean rewardIssued) {
		this.rewardIssued = rewardIssued;
	}

	@Override
	public String getNote() {
		return note;
	}

	@Override
	public void setNote(final String note) {
		this.note = note;
	}

}
