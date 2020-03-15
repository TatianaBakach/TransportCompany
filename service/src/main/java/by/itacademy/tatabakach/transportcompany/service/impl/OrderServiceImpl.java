package by.itacademy.tatabakach.transportcompany.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.tatabakach.transportcompany.daoapi.IOrderDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.OrderFilter;
import by.itacademy.tatabakach.transportcompany.service.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
    private IOrderDao dao;
	
    @Override
    public IOrder createEntity() {
        return dao.createEntity();
    }

    @Override
	public void save(final IOrder entity) {
		if (entity.getId() == null) {
			dao.insert(entity);
			LOGGER.info("new order create: {}", entity);
		} else {
			dao.update(entity);
			LOGGER.debug("order update: {}", entity);
		}
	}

    @Override
	public IOrder get(final Integer id) {
		final IOrder entity = dao.get(id);
		LOGGER.debug("entityById: {}", entity);
		return entity;
	}

    @Override
    public void delete(final Integer id) {
        // remove all references
        final IOrder iOrder = dao.get(id);
        iOrder.getEmployees().clear();
        dao.update(iOrder);
        LOGGER.info("delete entity: {}", id);
        dao.delete(id);
    }
    
    @Override
    public void deleteAll() {
    	LOGGER.info("delete all order entities");
		dao.deleteAll();
    }
    
    @Override
	public List<IOrder> getAll() {
		final List<IOrder> all = dao.selectAll();
		LOGGER.debug("total count in DB: {}", all.size());
		return all;
	}

    @Override
    public IOrder getFullInfo(final Integer id) {
        final IOrder entity = dao.getFullInfo(id);
        return entity;
    }

    @Override
    public long getCount(OrderFilter filter) {
        return dao.getCount(filter);
    }

    @Override
    public List<IOrder> find(OrderFilter filter) {
        return dao.find(filter);
    }

}
