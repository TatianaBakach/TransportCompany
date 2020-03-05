package by.itacademy.tatabakach.transportcompany.daoapi;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IDriver;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.DriverFilter;

public interface IDriverDao extends IDao<IDriver, Integer> {

	List<IDriver> find(DriverFilter filter);

	long getCount(DriverFilter filter);

}
