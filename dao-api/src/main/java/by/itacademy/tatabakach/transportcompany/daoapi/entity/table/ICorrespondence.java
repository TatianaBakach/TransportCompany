package by.itacademy.tatabakach.transportcompany.daoapi.entity.table;

import java.util.Date;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.enums.CorrespondenceType;

public interface ICorrespondence extends IBaseEntity {

	CorrespondenceType getCorrespondenceType();

	void setCorrespondenceType(CorrespondenceType correspondenceType);
	
	IOrder getOrder();
	
	void setOrder(IOrder order);
	
	ICompany getCompany();
	
	void setCompany(ICompany company);
	
	Date getDate();

	void setDate(Date date);
	
	String getContent();

	void setContent(String content);
	
	String getNote();

	void setNote(String note);


}
