package by.itacademy.tatabakach.transportcompany.daoapi.entity.table;

import java.util.Date;

public interface IContract extends IBaseEntity{
	
	String getNumber();

	void setNumber(String number);
	
	ICompany getOurCompany();
	
	void setOurCompany(ICompany ourCompany);
	
	ICompany getCompany();
	
	void setCompany(ICompany company);
	
	Date getDate();

	void setDate(Date date);

}
