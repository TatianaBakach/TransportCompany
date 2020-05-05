package by.itacademy.tatabakach.transportcompany.daoapi;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICar;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CarFilter;

public interface ICarDao extends IDao<ICar, Integer> {
	
	List<ICar> find(CarFilter filter);

	long getCount(CarFilter filter);

	List<ICar> search(String string);

}
