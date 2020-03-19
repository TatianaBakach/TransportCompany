package by.itacademy.tatabakach.transportcompany.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.tatabakach.transportcompany.daoapi.ITaxDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITax;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.TaxFilter;
import by.itacademy.tatabakach.transportcompany.service.ITaxService;

@Service
public class TaxServiceImpl implements ITaxService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaxServiceImpl.class);

	@Autowired
	private ITaxDao dao;

	@Override
	public ITax createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final ITax entity) {
		if (entity.getId() == null) {
			LOGGER.info("new tax create: {}", entity);
			dao.insert(entity);
		} else {
			LOGGER.debug("tax update: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public ITax get(final Integer id) {
		final ITax entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all taxes");
		dao.deleteAll();
	}

	@Override
	public List<ITax> find(final TaxFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(final TaxFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public List<ITax> getAll() {
		final List<ITax> all = dao.selectAll();
		return all;
	}

}
