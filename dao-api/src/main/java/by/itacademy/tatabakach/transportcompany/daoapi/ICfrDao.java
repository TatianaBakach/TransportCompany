package by.itacademy.tatabakach.transportcompany.daoapi;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICfr;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CfrFilter;

public interface ICfrDao extends IDao<ICfr, Integer>{
	
	List<ICfr> find(CfrFilter filter);

	long getCount(CfrFilter filter);
	
	ICfr getFullInfo(Integer id);


}
