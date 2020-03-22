package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IContract;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.ContractFilter;

public interface IContractService {
	
	IContract get(Integer id);

	List<IContract> getAll();

	@Transactional
	void save(IContract entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IContract createEntity();
	
	IContract getFullInfo(Integer id);

	List<IContract> find(ContractFilter filter);

	long getCount(ContractFilter filter);

}
