package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDistrict;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.DistrictFilter;

public interface IDistrictService {
	
	IDistrict get(Integer id);

    void save(IDistrict entity);

    void delete(Integer id);
    
    void deleteAll();

    IDistrict createEntity();

    List<IDistrict> getAll();
    
    IDistrict getFullInfo(Integer id);

    List<IDistrict> find(DistrictFilter filter);

    long getCount(DistrictFilter filter);

}
