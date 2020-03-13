package by.itacademy.tatabakach.transportcompany.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.tatabakach.transportcompany.daoapi.IOrderRewardPercentDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderRewardPercent;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.OrderRewardPercentFilter;
import by.itacademy.tatabakach.transportcompany.service.IOrderRewardPercentService;

@Service
public class OrderRewardPercentServiceImpl implements IOrderRewardPercentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderRewardPercentServiceImpl.class);

	@Autowired
	private IOrderRewardPercentDao dao;

	@Override
	public IOrderRewardPercent createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IOrderRewardPercent entity) {
		if (entity.getId() == null) {
			LOGGER.info("new percent create: {}", entity);
			dao.insert(entity);
		} else {
			LOGGER.debug("percent update: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public IOrderRewardPercent get(final Integer id) {
		final IOrderRewardPercent entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all percents");
		dao.deleteAll();
	}

	@Override
	public List<IOrderRewardPercent> find(final OrderRewardPercentFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(final OrderRewardPercentFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public List<IOrderRewardPercent> getAll() {
		final List<IOrderRewardPercent> all = dao.selectAll();
		return all;
	}

}
