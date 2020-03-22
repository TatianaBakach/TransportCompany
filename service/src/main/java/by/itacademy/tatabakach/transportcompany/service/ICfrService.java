package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICfr;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CfrFilter;

public interface ICfrService {

	ICfr get(Integer id);

	List<ICfr> getAll();

	@Transactional
	void save(ICfr entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	ICfr createEntity();

	ICfr getFullInfo(Integer id);

	List<ICfr> find(CfrFilter filter);

	long getCount(CfrFilter filter);

}
