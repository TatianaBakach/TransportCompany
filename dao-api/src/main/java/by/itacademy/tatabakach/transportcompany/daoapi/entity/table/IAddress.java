package by.itacademy.tatabakach.transportcompany.daoapi.entity.table;

public interface IAddress extends IBaseEntity{
	
	String getPostcode();

    void setPostcode(String postcode);

    ILocality getLocality();

    void setLocality(ILocality locality);
    
    String getExactAddress();
    
    void setExactAddress(String exactAddress);
    
    String getNote();
    
    void setNote(String note);

}
