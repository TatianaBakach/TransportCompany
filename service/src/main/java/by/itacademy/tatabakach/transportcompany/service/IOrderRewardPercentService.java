package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderRewardPercent;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.OrderRewardPercentFilter;

public interface IOrderRewardPercentService {
	
	IOrderRewardPercent get(Integer id);

	List<IOrderRewardPercent> getAll();

	@Transactional
	void save(IOrderRewardPercent entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IOrderRewardPercent createEntity();
	
	List<IOrderRewardPercent> find(OrderRewardPercentFilter filter);

	long getCount(OrderRewardPercentFilter filter);

}
