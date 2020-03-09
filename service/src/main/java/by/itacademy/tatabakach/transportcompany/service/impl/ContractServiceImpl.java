package by.itacademy.tatabakach.transportcompany.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.tatabakach.transportcompany.daoapi.IContractDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IContract;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.ContractFilter;
import by.itacademy.tatabakach.transportcompany.service.IContractService;

@Service
public class ContractServiceImpl implements IContractService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContractServiceImpl.class);

	@Autowired
	private IContractDao dao;

	@Override
	public IContract createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IContract entity) {
		if (entity.getId() == null) {
			dao.insert(entity);
			LOGGER.info("new contract create: {}", entity);
		} else {
			dao.update(entity);
			LOGGER.debug("contract update: {}", entity);
		}
	}

	@Override
	public IContract get(final Integer id) {
		final IContract entity = dao.get(id);
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
		LOGGER.info("delete all contracts entities");
		dao.deleteAll();
	}

	@Override
	public List<IContract> getAll() {
		final List<IContract> all = dao.selectAll();
		LOGGER.debug("total count in DB: {}", all.size());
		return all;
	}

	@Override
	public List<IContract> find(ContractFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(ContractFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public IContract getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}

}
