package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderRewardPercent;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.OrderRewardPercentFilter;

public interface IOrderRewardPercentService {
	
	IOrderRewardPercent get(Integer id);

	List<IOrderRewardPercent> getAll();

	void save(IOrderRewardPercent entity);

	void delete(Integer id);

	void deleteAll();

	IOrderRewardPercent createEntity();
	
	List<IOrderRewardPercent> find(OrderRewardPercentFilter filter);

	long getCount(OrderRewardPercentFilter filter);

}
