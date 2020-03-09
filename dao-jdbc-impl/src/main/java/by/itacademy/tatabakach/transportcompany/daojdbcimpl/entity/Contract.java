package by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity;

import java.util.Date;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IContract;

public class Contract extends BaseEntity implements IContract {

	private String number;

	private ICompany company;

	private Date date;

	@Override
	public String getNumber() {
		return number;
	}

	@Override
	public void setNumber(final String number) {
		this.number = number;
	}

	@Override
	public ICompany getCompany() {
		return company;
	}

	@Override
	public void setCompany(final ICompany company) {
		this.company = company;

	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public void setDate(final Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Contract [number=" + number + ", our_company=" + company + "company=" + company + "date=" + date
				+ ", getId()=" + getId() + "]";
	}

}
