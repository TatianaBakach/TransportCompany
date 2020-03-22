package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IPayment;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.PaymentFilter;

public interface IPaymentService {
	
	IPayment get(Integer id);

	List<IPayment> getAll();

	@Transactional
	void save(IPayment entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IPayment createEntity();
	
	IPayment getFullInfo(Integer id);

	List<IPayment> find(PaymentFilter filter);

	long getCount(PaymentFilter filter);

}
