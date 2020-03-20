package by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity;

import java.math.BigDecimal;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderRewardPercent;

public class OrderRewardPercent extends BaseEntity implements IOrderRewardPercent {
	
	private BigDecimal percent;

	@Override
	public BigDecimal getPercent() {
		return percent;
	}

	@Override
	public void setPercent(final BigDecimal percent) {
		this.percent = percent;
	}

}
