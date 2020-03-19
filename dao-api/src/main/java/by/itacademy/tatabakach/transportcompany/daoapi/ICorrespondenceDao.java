package by.itacademy.tatabakach.transportcompany.daoapi;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICorrespondence;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CorrespondenceFilter;

public interface ICorrespondenceDao extends IDao<ICorrespondence, Integer> {
	
	List<ICorrespondence> find(CorrespondenceFilter filter);

	long getCount(CorrespondenceFilter filter);

	ICorrespondence getFullInfo(Integer id);

}
