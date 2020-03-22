package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.OrderFilter;

public interface IOrderService {

	IOrder get(Integer id);

	List<IOrder> getAll();

	@Transactional
	void save(IOrder entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IOrder createEntity();

	IOrder getFullInfo(final Integer id);

	List<IOrder> find(OrderFilter filter);

	long getCount(OrderFilter filter);

}
