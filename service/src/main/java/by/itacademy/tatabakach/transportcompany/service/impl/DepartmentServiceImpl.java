package by.itacademy.tatabakach.transportcompany.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.tatabakach.transportcompany.daoapi.IDepartmentDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDepartment;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.DepartmentFilter;
import by.itacademy.tatabakach.transportcompany.service.IDepartmentService;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);

	@Autowired
	private IDepartmentDao dao;

	@Override
	public IDepartment createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IDepartment entity) {
		if (entity.getId() == null) {
			LOGGER.info("new department create: {}", entity);
			dao.insert(entity);
		} else {
			LOGGER.debug("department update: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public IDepartment get(final Integer id) {
		final IDepartment entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all departments");
		dao.deleteAll();
	}

	@Override
	public List<IDepartment> find(final DepartmentFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(final DepartmentFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public List<IDepartment> getAll() {
		final List<IDepartment> all = dao.selectAll();
		return all;
	}

}
