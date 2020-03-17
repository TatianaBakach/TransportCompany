package by.itacademy.tatabakach.transportcompany.daoapi;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IPayment;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.PaymentFilter;

public interface IPaymentDao extends IDao<IPayment, Integer>{
	
	List<IPayment> find(PaymentFilter filter);

	long getCount(PaymentFilter filter);

	IPayment getFullInfo(Integer id);

}
