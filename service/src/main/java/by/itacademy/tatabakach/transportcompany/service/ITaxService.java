package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITax;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.TaxFilter;

public interface ITaxService {
	
	ITax get(Integer id);

	List<ITax> getAll();

	void save(ITax entity);

	void delete(Integer id);

	void deleteAll();

	ITax createEntity();
	
	List<ITax> find(TaxFilter filter);

	long getCount(TaxFilter filter);

}
