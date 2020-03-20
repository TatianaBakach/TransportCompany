package by.itacademy.tatabakach.transportcompany.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.OrderRewardPercent;
import by.itacademy.tatabakach.transportcompany.daoapi.IOrderRewardPercentDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderRewardPercent;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.OrderRewardPercentFilter;

@Repository
public class OrderRewardPercentDaoImpl extends AbstractDaoImpl<IOrderRewardPercent, Integer>
		implements IOrderRewardPercentDao {

	protected OrderRewardPercentDaoImpl() {
		super(OrderRewardPercent.class);
	}

	@Override
	public IOrderRewardPercent createEntity() {
		return new OrderRewardPercent();
	}

	@Override
	public List<IOrderRewardPercent> find(OrderRewardPercentFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(OrderRewardPercentFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

}
