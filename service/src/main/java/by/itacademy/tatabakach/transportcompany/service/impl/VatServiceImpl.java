package by.itacademy.tatabakach.transportcompany.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.tatabakach.transportcompany.daoapi.IVatDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IVat;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.VatFilter;
import by.itacademy.tatabakach.transportcompany.service.IVatService;

@Service
public class VatServiceImpl implements IVatService {

	private static final Logger LOGGER = LoggerFactory.getLogger(VatServiceImpl.class);

	@Autowired
	private IVatDao dao;

	@Override
	public IVat createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IVat entity) {
		if (entity.getId() == null) {
			LOGGER.info("new vat create: {}", entity);
			dao.insert(entity);
		} else {
			LOGGER.debug("vat update: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public IVat get(final Integer id) {
		final IVat entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all vats");
		dao.deleteAll();
	}

	@Override
	public List<IVat> find(final VatFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(final VatFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public List<IVat> getAll() {
		final List<IVat> all = dao.selectAll();
		return all;
	}

}
