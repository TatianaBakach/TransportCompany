package by.itacademy.tatabakach.transportcompany.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.tatabakach.transportcompany.daoapi.IPaymentDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IPayment;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.PaymentFilter;
import by.itacademy.tatabakach.transportcompany.service.IPaymentService;

@Service
public class PaymentServiceImpl implements IPaymentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Autowired
	private IPaymentDao dao;

	@Override
	public IPayment createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IPayment entity) {
		if (entity.getId() == null) {
			dao.insert(entity);
			LOGGER.info("new payment create: {}", entity);
		} else {
			dao.update(entity);
			LOGGER.debug("payment update: {}", entity);
		}
	}

	@Override
	public IPayment get(final Integer id) {
		final IPayment entity = dao.get(id);
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
		LOGGER.info("delete all payment entities");
		dao.deleteAll();
	}

	@Override
	public List<IPayment> getAll() {
		final List<IPayment> all = dao.selectAll();
		LOGGER.debug("total count in DB: {}", all.size());
		return all;
	}

	@Override
	public List<IPayment> find(PaymentFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(PaymentFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public IPayment getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}

}
