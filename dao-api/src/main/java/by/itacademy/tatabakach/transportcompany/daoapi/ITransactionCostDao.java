package by.itacademy.tatabakach.transportcompany.daoapi;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITransactionCost;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.TransactionCostFilter;

public interface ITransactionCostDao extends IDao<ITransactionCost, Integer> {

    List<ITransactionCost> find(TransactionCostFilter filter);

    long getCount(TransactionCostFilter filter);

}
