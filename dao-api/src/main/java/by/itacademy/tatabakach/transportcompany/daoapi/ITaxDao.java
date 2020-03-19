package by.itacademy.tatabakach.transportcompany.daoapi;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITax;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.TaxFilter;

public interface ITaxDao extends IDao<ITax, Integer> {
	
	List<ITax> find(TaxFilter filter);

	long getCount(TaxFilter filter);

}
