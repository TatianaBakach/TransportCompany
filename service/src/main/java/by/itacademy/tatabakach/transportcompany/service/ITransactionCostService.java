package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITransactionCost;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.TransactionCostFilter;

public interface ITransactionCostService {
	
	ITransactionCost get(Integer id);

	List<ITransactionCost> getAll();

	void save(ITransactionCost entity);

	void delete(Integer id);

	void deleteAll();

	ITransactionCost createEntity();

	List<ITransactionCost> find(TransactionCostFilter filter);

	long getCount(TransactionCostFilter filter);


}
