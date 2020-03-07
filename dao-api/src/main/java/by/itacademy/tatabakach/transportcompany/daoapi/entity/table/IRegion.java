package by.itacademy.tatabakach.transportcompany.daoapi.entity.table;

public interface IRegion extends IBaseEntity {
	
	String getName();

    void setName(String name);

    ICountry getCountry();

    void setCountry(ICountry country);

}
