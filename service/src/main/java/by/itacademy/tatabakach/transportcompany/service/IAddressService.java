package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IAddress;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.AddressFilter;

public interface IAddressService {
	
	IAddress get(Integer id);

    void save(IAddress entity);

    void delete(Integer id);
    
    void deleteAll();

    IAddress createEntity();

    List<IAddress> getAll();
    
    IAddress getFullInfo(Integer id);

    List<IAddress> find(AddressFilter filter);

    long getCount(AddressFilter filter);


}
