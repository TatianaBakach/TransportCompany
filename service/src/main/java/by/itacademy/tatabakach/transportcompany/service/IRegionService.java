package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRegion;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.RegionFilter;

public interface IRegionService {
	
	IRegion get(Integer id);

    @Transactional
	void save(IRegion entity);

    @Transactional
    void delete(Integer id);
    
    @Transactional
    void deleteAll();

    IRegion createEntity();

    List<IRegion> getAll();
    
    IRegion getFullInfo(Integer id);

    List<IRegion> find(RegionFilter filter);

    long getCount(RegionFilter filter);
   
}
