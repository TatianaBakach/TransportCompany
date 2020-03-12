package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICfr;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CfrFilter;

public interface ICfrService {

	ICfr get(Integer id);

	List<ICfr> getAll();

	void save(ICfr entity);

	void delete(Integer id);

	void deleteAll();

	ICfr createEntity();

	ICfr getFullInfo(Integer id);

	List<ICfr> find(CfrFilter filter);

	long getCount(CfrFilter filter);

}
