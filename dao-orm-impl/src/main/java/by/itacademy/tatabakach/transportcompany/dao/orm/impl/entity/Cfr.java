package by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICfr;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;

@Entity
public class Cfr extends BaseEntity implements ICfr {

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Company.class)
	private ICompany company;

	@Column
	private Integer year;

	@Override
	public ICompany getCompany() {
		return company;
	}

	@Override
	public void setCompany(final ICompany company) {
		this.company = company;

	}

	@Override
	public Integer getYear() {
		return year;
	}

	@Override
	public void setYear(final Integer year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Cfr [company=" + company + "year=" + year + ", getId()=" + getId() + "]";
	}

}
