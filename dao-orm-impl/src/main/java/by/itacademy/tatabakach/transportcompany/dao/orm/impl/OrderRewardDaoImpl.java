package by.itacademy.tatabakach.transportcompany.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.OrderReward;
import by.itacademy.tatabakach.transportcompany.daoapi.IOrderRewardDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderReward;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.OrderRewardFilter;

@Repository
public class OrderRewardDaoImpl extends AbstractDaoImpl<IOrderReward, Integer> implements IOrderRewardDao {
	
	protected OrderRewardDaoImpl() {
		super(OrderReward.class);
	}


	@Override
	public IOrderReward createEntity() {
		return new OrderReward();
	}


	@Override
	public IOrderReward getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<IOrderReward> find(OrderRewardFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public long getCount(OrderRewardFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

}
