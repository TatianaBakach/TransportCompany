package by.itacademy.tatabakach.transportcompany.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.tatabakach.transportcompany.daoapi.IDistrictDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDistrict;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.DistrictFilter;
import by.itacademy.tatabakach.transportcompany.service.IDistrictService;

@Service
public class DistrictServiceImpl implements IDistrictService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DistrictServiceImpl.class);

	@Autowired
	private IDistrictDao dao;

	@Override
	public IDistrict createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IDistrict entity) {
		if (entity.getId() == null) {
			LOGGER.info("new district create: {}", entity);
			dao.insert(entity);
		} else {
			LOGGER.debug("district update: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public IDistrict get(final Integer id) {
		final IDistrict entity = dao.get(id);
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
		LOGGER.info("delete all districts");
		dao.deleteAll();
	}

	@Override
	public List<IDistrict> getAll() {
		final List<IDistrict> all = dao.selectAll();
		LOGGER.debug("total count in DB: {}", all.size());
		return all;
	}

	@Override
	public List<IDistrict> find(final DistrictFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(final DistrictFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public IDistrict getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}

}
