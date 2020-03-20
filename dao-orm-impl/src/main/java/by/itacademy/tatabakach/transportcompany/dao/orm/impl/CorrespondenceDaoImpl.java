package by.itacademy.tatabakach.transportcompany.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Correspondence;
import by.itacademy.tatabakach.transportcompany.daoapi.ICorrespondenceDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICorrespondence;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CorrespondenceFilter;

@Repository
public class CorrespondenceDaoImpl extends AbstractDaoImpl<ICorrespondence, Integer> implements ICorrespondenceDao {

	protected CorrespondenceDaoImpl() {
		super(Correspondence.class);
	}

	@Override
	public ICorrespondence createEntity() {
		return new Correspondence();
	}

	@Override
	public List<ICorrespondence> find(CorrespondenceFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(CorrespondenceFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ICorrespondence getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
