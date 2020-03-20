package by.itacademy.tatabakach.transportcompany.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Contract;
import by.itacademy.tatabakach.transportcompany.daoapi.IContractDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IContract;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.ContractFilter;

@Repository
public class ContractDaoImpl extends AbstractDaoImpl<IContract, Integer> implements IContractDao {


	protected ContractDaoImpl() {
		super(Contract.class);
	}

	@Override
	public IContract createEntity() {
		return new Contract();
	}

	@Override
	public List<IContract> find(ContractFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(ContractFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IContract getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
