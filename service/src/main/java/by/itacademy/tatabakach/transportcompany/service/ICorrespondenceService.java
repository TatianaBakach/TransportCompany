package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICorrespondence;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CorrespondenceFilter;

public interface ICorrespondenceService {
	
	ICorrespondence get(Integer id);

	List<ICorrespondence> getAll();

	@Transactional
	void save(ICorrespondence entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	ICorrespondence createEntity();
	
	ICorrespondence getFullInfo(Integer id);

	List<ICorrespondence> find(CorrespondenceFilter filter);

	long getCount(CorrespondenceFilter filter);

}
