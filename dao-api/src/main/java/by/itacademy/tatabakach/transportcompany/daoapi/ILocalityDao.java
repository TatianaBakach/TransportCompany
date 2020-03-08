package by.itacademy.tatabakach.transportcompany.daoapi;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ILocality;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.LocalityFilter;

public interface ILocalityDao extends IDao<ILocality, Integer> {
	
	ILocality getFullInfo(final Integer id);

	List<ILocality> find(LocalityFilter filter);

	long getCount(LocalityFilter filter);

}
