package by.itacademy.tatabakach.transportcompany.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.tatabakach.transportcompany.daoapi.IEmployeeDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.EmployeeFilter;
import by.itacademy.tatabakach.transportcompany.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private IEmployeeDao dao;

	@Override
	public IEmployee createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IEmployee entity) {
		if (entity.getId() == null) {
			dao.insert(entity);
			LOGGER.info("new employee create: {}", entity);
		} else {
			dao.update(entity);
			LOGGER.debug("employee update: {}", entity);
		}
	}

	@Override
	public IEmployee get(final Integer id) {
		final IEmployee entity = dao.get(id);
		LOGGER.debug("entityById: {}", entity);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
	}
	
	@Override
	public void deleteAll() {
		LOGGER.info("delete all employee entities");
		dao.deleteAll();
	}

	@Override
	public List<IEmployee> getAll() {
		final List<IEmployee> all = dao.selectAll();
		LOGGER.debug("total count in DB: {}", all.size());
		return all;
	}

	@Override
	public List<IEmployee> find(EmployeeFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(EmployeeFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public IEmployee getByUserName(String username) {
		return dao.getByUserName(username);
	}

}
