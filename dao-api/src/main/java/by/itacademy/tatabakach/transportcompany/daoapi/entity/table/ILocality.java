package by.itacademy.tatabakach.transportcompany.daoapi.entity.table;

public interface ILocality extends IBaseEntity{
	
	String getName();

    void setName(String name);

    IDistrict getDistrict();

    void setDistrict(IDistrict district);

}
