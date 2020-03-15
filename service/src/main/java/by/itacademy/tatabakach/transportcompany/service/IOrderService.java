package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.OrderFilter;

public interface IOrderService {

	IOrder get(Integer id);

	List<IOrder> getAll();

	void save(IOrder entity);

	void delete(Integer id);

	void deleteAll();

	IOrder createEntity();

	IOrder getFullInfo(final Integer id);

	List<IOrder> find(OrderFilter filter);

	long getCount(OrderFilter filter);

}
