package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IPayment;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.PaymentFilter;

public interface IPaymentService {
	
	IPayment get(Integer id);

	List<IPayment> getAll();

	void save(IPayment entity);

	void delete(Integer id);

	void deleteAll();

	IPayment createEntity();
	
	IPayment getFullInfo(Integer id);

	List<IPayment> find(PaymentFilter filter);

	long getCount(PaymentFilter filter);

}
