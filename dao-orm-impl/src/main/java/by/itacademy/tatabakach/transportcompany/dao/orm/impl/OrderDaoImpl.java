package by.itacademy.tatabakach.transportcompany.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Order;
import by.itacademy.tatabakach.transportcompany.daoapi.IOrderDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.OrderFilter;

@Repository
public class OrderDaoImpl extends AbstractDaoImpl<IOrder, Integer> implements IOrderDao {


	protected OrderDaoImpl() {
		super(Order.class);
	}

	@Override
	public IOrder createEntity() {
		return new Order();
	}

	@Override
	public List<IOrder> find(OrderFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(OrderFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IOrder getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
