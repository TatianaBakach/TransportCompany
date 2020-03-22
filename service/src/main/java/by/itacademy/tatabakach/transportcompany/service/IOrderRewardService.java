package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderReward;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.OrderRewardFilter;

public interface IOrderRewardService {
	
	IOrderReward get(Integer id);

	List<IOrderReward> getAll();

	@Transactional
	void save(IOrderReward entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IOrderReward createEntity();
	
	IOrderReward getFullInfo(Integer id);

	List<IOrderReward> find(OrderRewardFilter filter);

	long getCount(OrderRewardFilter filter);

}
