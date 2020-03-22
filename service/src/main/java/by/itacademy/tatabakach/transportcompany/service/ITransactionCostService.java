package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITransactionCost;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.TransactionCostFilter;

public interface ITransactionCostService {
	
	ITransactionCost get(Integer id);

	List<ITransactionCost> getAll();

	@Transactional
	void save(ITransactionCost entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	ITransactionCost createEntity();

	List<ITransactionCost> find(TransactionCostFilter filter);

	long getCount(TransactionCostFilter filter);


}
