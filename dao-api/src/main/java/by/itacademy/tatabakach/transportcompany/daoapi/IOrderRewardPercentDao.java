package by.itacademy.tatabakach.transportcompany.daoapi;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderRewardPercent;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.OrderRewardPercentFilter;

public interface IOrderRewardPercentDao extends IDao<IOrderRewardPercent, Integer> {
	
	List<IOrderRewardPercent> find(OrderRewardPercentFilter filter);

	long getCount(OrderRewardPercentFilter filter);

}
