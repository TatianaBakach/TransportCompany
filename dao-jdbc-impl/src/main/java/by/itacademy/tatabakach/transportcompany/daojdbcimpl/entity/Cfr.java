package by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICfr;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;

public class Cfr extends BaseEntity implements ICfr {

	private ICompany company;

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
