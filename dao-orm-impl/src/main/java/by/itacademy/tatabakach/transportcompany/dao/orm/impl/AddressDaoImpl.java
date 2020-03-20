package by.itacademy.tatabakach.transportcompany.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Address;
import by.itacademy.tatabakach.transportcompany.daoapi.IAddressDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IAddress;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.AddressFilter;

@Repository
public class AddressDaoImpl extends AbstractDaoImpl<IAddress, Integer> implements IAddressDao {

	protected AddressDaoImpl() {
		super(Address.class);
	}

	@Override
	public IAddress createEntity() {
		return new Address();
	}

	@Override
	public IAddress getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IAddress> find(AddressFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(AddressFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

}
