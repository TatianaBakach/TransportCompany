package by.itacademy.tatabakach.transportcompany.daoapi;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IPosition;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.PositionFilter;

public interface IPositionDao extends IDao<IPosition, Integer> {
	
	List<IPosition> find(PositionFilter filter);

	long getCount(PositionFilter filter);


}
