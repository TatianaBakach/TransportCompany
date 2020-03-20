package by.itacademy.tatabakach.transportcompany.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Driver;
import by.itacademy.tatabakach.transportcompany.daoapi.IDriverDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDriver;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.DriverFilter;

@Repository
public class DriverDaoImpl extends AbstractDaoImpl<IDriver, Integer> implements IDriverDao {

	protected DriverDaoImpl() {
		super(Driver.class);
	}

	@Override
	public IDriver createEntity() {
		return new Driver();
	}

	@Override
	public List<IDriver> find(DriverFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(DriverFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

}
