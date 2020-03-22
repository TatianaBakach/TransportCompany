package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IAddress;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.AddressFilter;

public interface IAddressService {
	
	IAddress get(Integer id);

    @Transactional
	void save(IAddress entity);

    @Transactional
    void delete(Integer id);
    
    @Transactional
    void deleteAll();

    IAddress createEntity();

    List<IAddress> getAll();
    
    IAddress getFullInfo(Integer id);

    List<IAddress> find(AddressFilter filter);

    long getCount(AddressFilter filter);


}
