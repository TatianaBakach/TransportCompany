package by.itacademy.tatabakach.transportcompany.daoapi;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderReward;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.OrderRewardFilter;

public interface IOrderRewardDao extends IDao<IOrderReward, Integer> {
	
	IOrderReward getFullInfo(final Integer id);

	List<IOrderReward> find(OrderRewardFilter filter);

	long getCount(OrderRewardFilter filter);

}
