package by.itacademy.tatabakach.transportcompany.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.tatabakach.transportcompany.daoapi.ITransactionCostDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITransactionCost;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.TransactionCostFilter;
import by.itacademy.tatabakach.transportcompany.service.ITransactionCostService;

@Service
public class TransactionCostServiceImpl implements ITransactionCostService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionCostServiceImpl.class);

	@Autowired
	private ITransactionCostDao dao;

	@Override
	public ITransactionCost createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final ITransactionCost entity) {
		if (entity.getId() == null) {
			dao.insert(entity);
			LOGGER.info("new transaction_cost create: {}", entity);
		} else {
			dao.update(entity);
			LOGGER.debug("transaction_cost update: {}", entity);
		}
	}

	@Override
	public ITransactionCost get(final Integer id) {
		final ITransactionCost entity = dao.get(id);
		LOGGER.debug("entityById: {}", entity);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		LOGGER.info("delete entity: {}", id);
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all transaction_cost entities");
		dao.deleteAll();
	}

	@Override
	public List<ITransactionCost> getAll() {
		final List<ITransactionCost> all = dao.selectAll();
		LOGGER.debug("total count in DB: {}", all.size());
		return all;
	}

	@Override
	public List<ITransactionCost> find(TransactionCostFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(TransactionCostFilter filter) {
		return dao.getCount(filter);
	}

}
