package by.itacademy.tatabakach.transportcompany.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Cfr;
import by.itacademy.tatabakach.transportcompany.daoapi.ICfrDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICfr;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CfrFilter;

@Repository
public class CfrDaoImpl extends AbstractDaoImpl<ICfr, Integer> implements ICfrDao {


	protected CfrDaoImpl() {
		super(Cfr.class);
	}

	@Override
	public ICfr createEntity() {
		return new Cfr();
	}

	@Override
	public List<ICfr> find(CfrFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(CfrFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ICfr getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
