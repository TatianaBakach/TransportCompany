package by.itacademy.tatabakach.transportcompany.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.tatabakach.transportcompany.daoapi.IOrderRewardDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderReward;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.OrderRewardFilter;
import by.itacademy.tatabakach.transportcompany.service.IOrderRewardService;

@Service
public class OrderRewardServiceImpl implements IOrderRewardService {

	private static final Logger LOGGER = LoggerFactory.getLogger( OrderRewardServiceImpl.class);

	@Autowired
	private IOrderRewardDao dao;

	@Override
	public IOrderReward createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IOrderReward entity) {
		if (entity.getId() == null) {
			dao.insert(entity);
			LOGGER.info("new order reward create: {}", entity);
		} else {
			dao.update(entity);
			LOGGER.debug("order reward update: {}", entity);
		}
	}

	@Override
	public IOrderReward get(final Integer id) {
		final IOrderReward entity = dao.get(id);
		LOGGER.debug("entityById: {}", entity);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		LOGGER.info("delete entity: {}", id);
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all order reward entities");
		dao.deleteAll();
	}

	@Override
	public List<IOrderReward> getAll() {
		final List<IOrderReward> all = dao.selectAll();
		LOGGER.debug("total count in DB: {}", all.size());
		return all;
	}

	@Override
	public List<IOrderReward> find(OrderRewardFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(OrderRewardFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public IOrderReward getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}

}
