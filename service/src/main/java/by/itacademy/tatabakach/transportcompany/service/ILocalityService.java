package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ILocality;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.LocalityFilter;

public interface ILocalityService {
	
	ILocality get(Integer id);

    @Transactional
	void save(ILocality entity);

    @Transactional
    void delete(Integer id);
    
    @Transactional
    void deleteAll();

    ILocality createEntity();

    List<ILocality> getAll();
    
    ILocality getFullInfo(Integer id);

    List<ILocality> find(LocalityFilter filter);

    long getCount(LocalityFilter filter);

}
