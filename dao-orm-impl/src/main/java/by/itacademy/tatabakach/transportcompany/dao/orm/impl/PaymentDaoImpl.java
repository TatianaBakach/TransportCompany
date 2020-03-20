package by.itacademy.tatabakach.transportcompany.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Payment;
import by.itacademy.tatabakach.transportcompany.daoapi.IPaymentDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IPayment;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.PaymentFilter;

@Repository
public class PaymentDaoImpl extends AbstractDaoImpl<IPayment, Integer> implements IPaymentDao{
	
	protected PaymentDaoImpl() {
		super(Payment.class);
	}

	@Override
	public IPayment createEntity() {
		return new Payment();
	}

	@Override
	public List<IPayment> find(PaymentFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(PaymentFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IPayment getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
