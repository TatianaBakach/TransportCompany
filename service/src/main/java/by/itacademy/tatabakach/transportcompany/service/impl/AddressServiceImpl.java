package by.itacademy.tatabakach.transportcompany.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.tatabakach.transportcompany.daoapi.IAddressDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IAddress;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.AddressFilter;
import by.itacademy.tatabakach.transportcompany.service.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AddressServiceImpl.class);

	@Autowired
	private IAddressDao dao;

	@Override
	public IAddress createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IAddress entity) {
		if (entity.getId() == null) {
			LOGGER.info("new address create: {}", entity);
			dao.insert(entity);
		} else {
			LOGGER.debug("address update: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public IAddress get(final Integer id) {
		final IAddress entity = dao.get(id);
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
		LOGGER.info("delete all addresses");
		dao.deleteAll();
	}

	@Override
	public List<IAddress> getAll() {
		final List<IAddress> all = dao.selectAll();
		LOGGER.debug("total count in DB: {}", all.size());
		return all;
	}

	@Override
	public List<IAddress> find(final AddressFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(final AddressFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public IAddress getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}

}
