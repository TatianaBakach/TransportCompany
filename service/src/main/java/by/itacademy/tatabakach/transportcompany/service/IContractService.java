package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IContract;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.ContractFilter;

public interface IContractService {
	
	IContract get(Integer id);

	List<IContract> getAll();

	void save(IContract entity);

	void delete(Integer id);

	void deleteAll();

	IContract createEntity();
	
	IContract getFullInfo(Integer id);

	List<IContract> find(ContractFilter filter);

	long getCount(ContractFilter filter);

}
