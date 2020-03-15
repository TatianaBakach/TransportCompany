package by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity;

import java.math.BigDecimal;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IVat;

public class Vat extends BaseEntity implements IVat {
	
	private String name;
	private BigDecimal rate;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}
	
	@Override
	public BigDecimal getRate() {
		return rate;
	}

	@Override
	public void setRate(final BigDecimal rate) {
		this.rate = rate;
	}


}
