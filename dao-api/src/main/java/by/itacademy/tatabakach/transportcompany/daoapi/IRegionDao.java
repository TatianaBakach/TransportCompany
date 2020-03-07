package by.itacademy.tatabakach.transportcompany.daoapi;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRegion;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.RegionFilter;

public interface IRegionDao extends IDao<IRegion, Integer> {

	IRegion getFullInfo(final Integer id);

	List<IRegion> find(RegionFilter filter);

	long getCount(RegionFilter filter);

}
