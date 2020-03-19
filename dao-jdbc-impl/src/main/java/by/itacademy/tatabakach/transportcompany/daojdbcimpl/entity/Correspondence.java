package by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity;

import java.util.Date;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.CorrespondenceType;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICorrespondence;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;

public class Correspondence extends BaseEntity implements ICorrespondence {
	
	private CorrespondenceType correspondenceType;
	
	private IOrder order;
	
	private ICompany company;
	
	private Date date;
	
	private String 	content;
	
	private String note;

	@Override
	public CorrespondenceType getCorrespondenceType() {
		return correspondenceType;
	}

	@Override
	public void setCorrespondenceType(final CorrespondenceType correspondenceType) {
		this.correspondenceType = correspondenceType;
	}

	@Override
	public IOrder getOrder() {
		return order;
	}

	@Override
	public void setOrder(final IOrder order) {
		this.order = order;
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
	public String getContent() {
		return content;
	}

	@Override
	public void setContent(final String content) {
		this.content = content;
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
