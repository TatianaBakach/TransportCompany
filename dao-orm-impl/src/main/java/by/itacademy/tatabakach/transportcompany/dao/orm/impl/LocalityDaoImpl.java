package by.itacademy.tatabakach.transportcompany.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Locality;
import by.itacademy.tatabakach.transportcompany.daoapi.ILocalityDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ILocality;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.LocalityFilter;

@Repository
public class LocalityDaoImpl extends AbstractDaoImpl<ILocality, Integer> implements ILocalityDao {

	protected LocalityDaoImpl() {
		super(Locality.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ILocality createEntity() {
		return new Locality();
	}

	@Override
	public ILocality getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ILocality> find(LocalityFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(LocalityFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

}
