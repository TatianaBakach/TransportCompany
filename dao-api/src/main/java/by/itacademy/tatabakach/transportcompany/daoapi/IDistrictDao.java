package by.itacademy.tatabakach.transportcompany.daoapi;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDistrict;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.DistrictFilter;

public interface IDistrictDao extends IDao<IDistrict, Integer> {
	
	IDistrict getFullInfo(final Integer id);

	List<IDistrict> find(DistrictFilter filter);

	long getCount(DistrictFilter filter);

}
