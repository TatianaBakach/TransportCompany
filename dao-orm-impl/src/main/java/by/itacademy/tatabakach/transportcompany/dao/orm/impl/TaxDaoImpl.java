package by.itacademy.tatabakach.transportcompany.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Tax;
import by.itacademy.tatabakach.transportcompany.daoapi.ITaxDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITax;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.TaxFilter;

@Repository
public class TaxDaoImpl extends AbstractDaoImpl<ITax, Integer> implements ITaxDao {

	protected TaxDaoImpl() {
		super(Tax.class);
	}

	@Override
	public ITax createEntity() {
		return new Tax();
	}

	@Override
	public List<ITax> find(TaxFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(TaxFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

}
