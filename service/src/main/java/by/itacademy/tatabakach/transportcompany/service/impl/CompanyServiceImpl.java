package by.itacademy.tatabakach.transportcompany.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.tatabakach.transportcompany.daoapi.ICompanyDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CompanyFilter;
import by.itacademy.tatabakach.transportcompany.service.ICompanyService;

@Service
public class CompanyServiceImpl implements ICompanyService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);

	@Autowired
	private ICompanyDao dao;

	@Override
	public ICompany createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final ICompany entity) {
		if (entity.getId() == null) {
			dao.insert(entity);
			LOGGER.info("new company create: {}", entity);
		} else {
			dao.update(entity);
			LOGGER.debug("company update: {}", entity);
		}
	}

	@Override
	public ICompany get(final Integer id) {
		final ICompany entity = dao.get(id);
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
		LOGGER.info("delete all company entities");
		dao.deleteAll();
	}

	@Override
	public List<ICompany> getAll() {
		final List<ICompany> all = dao.selectAll();
		LOGGER.debug("total count in DB: {}", all.size());
		return all;
	}

	@Override
	public List<ICompany> find(CompanyFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(CompanyFilter filter) {
		return dao.getCount(filter);
	}
	
	@Override
	public ICompany getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}

}
