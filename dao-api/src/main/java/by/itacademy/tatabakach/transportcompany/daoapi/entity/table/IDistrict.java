package by.itacademy.tatabakach.transportcompany.daoapi.entity.table;

public interface IDistrict extends IBaseEntity{
	
	String getName();

    void setName(String name);

    IRegion getRegion();

    void setRegion(IRegion region);

}
