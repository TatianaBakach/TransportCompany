package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ILocality;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.LocalityFilter;

public interface ILocalityService {
	
	ILocality get(Integer id);

    void save(ILocality entity);

    void delete(Integer id);
    
    void deleteAll();

    ILocality createEntity();

    List<ILocality> getAll();
    
    ILocality getFullInfo(Integer id);

    List<ILocality> find(LocalityFilter filter);

    long getCount(LocalityFilter filter);

}
