package by.itacademy.tatabakach.transportcompany.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Region;
import by.itacademy.tatabakach.transportcompany.daoapi.IRegionDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRegion;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.RegionFilter;

@Repository
public class RegionDaoImpl extends AbstractDaoImpl<IRegion, Integer> implements IRegionDao {

	protected RegionDaoImpl() {
		super(Region.class);
	}

	@Override
	public IRegion createEntity() {
		return new Region();
	}

	@Override
	public IRegion getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IRegion> find(RegionFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(RegionFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

}
