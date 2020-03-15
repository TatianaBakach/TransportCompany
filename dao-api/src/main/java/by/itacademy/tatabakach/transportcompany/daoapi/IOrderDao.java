package by.itacademy.tatabakach.transportcompany.daoapi;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.OrderFilter;

public interface IOrderDao extends IDao<IOrder, Integer> {
	
	List<IOrder> find(OrderFilter filter);

	long getCount(OrderFilter filter);

	IOrder getFullInfo(Integer id);

}
