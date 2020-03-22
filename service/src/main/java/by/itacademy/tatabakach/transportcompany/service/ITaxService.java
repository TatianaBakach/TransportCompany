package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITax;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.TaxFilter;

public interface ITaxService {
	
	ITax get(Integer id);

	List<ITax> getAll();

	@Transactional
	void save(ITax entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	ITax createEntity();
	
	List<ITax> find(TaxFilter filter);

	long getCount(TaxFilter filter);

}
