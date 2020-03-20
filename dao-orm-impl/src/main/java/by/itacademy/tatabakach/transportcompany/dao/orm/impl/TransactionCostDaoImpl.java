package by.itacademy.tatabakach.transportcompany.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.TransactionCost;
import by.itacademy.tatabakach.transportcompany.daoapi.ITransactionCostDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITransactionCost;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.TransactionCostFilter;

@Repository
public class TransactionCostDaoImpl extends AbstractDaoImpl<ITransactionCost, Integer> implements ITransactionCostDao {

	protected TransactionCostDaoImpl() {
		super(TransactionCost.class);
	}

	@Override
	public ITransactionCost createEntity() {
		return new TransactionCost();
	}

	@Override
	public List<ITransactionCost> find(TransactionCostFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(TransactionCostFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

}
