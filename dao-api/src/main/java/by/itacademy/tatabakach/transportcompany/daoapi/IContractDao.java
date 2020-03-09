package by.itacademy.tatabakach.transportcompany.daoapi;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IContract;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.ContractFilter;

public interface IContractDao extends IDao<IContract, Integer> {
	
	List<IContract> find(ContractFilter filter);

	long getCount(ContractFilter filter);

	IContract getFullInfo(Integer id);

}
