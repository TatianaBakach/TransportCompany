package by.itacademy.tatabakach.transportcompany.daoapi;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IAddress;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.AddressFilter;

public interface IAddressDao extends IDao<IAddress, Integer> {
	
	IAddress getFullInfo(final Integer id);

	List<IAddress> find(AddressFilter filter);

	long getCount(AddressFilter filter);

}
