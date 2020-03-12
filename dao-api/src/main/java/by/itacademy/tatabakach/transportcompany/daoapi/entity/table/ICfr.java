package by.itacademy.tatabakach.transportcompany.daoapi.entity.table;

public interface ICfr extends IBaseEntity {

	ICompany getCompany();

	void setCompany(ICompany company);
	
	Integer getYear();
	
	void setYear(Integer year);

}
