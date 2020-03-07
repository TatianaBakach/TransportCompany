package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRegion;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.RegionFilter;

public interface IRegionService {
	
	IRegion get(Integer id);

    void save(IRegion entity);

    void delete(Integer id);
    
    void deleteAll();

    IRegion createEntity();

    List<IRegion> getAll();
    
    IRegion getFullInfo(Integer id);

    List<IRegion> find(RegionFilter filter);

    long getCount(RegionFilter filter);
   
}
