package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IPayment;
import by.itacademy.tatabakach.transportcompany.service.ICompanyService;
import by.itacademy.tatabakach.transportcompany.service.IOrderService;
import by.itacademy.tatabakach.transportcompany.service.IPaymentService;
import by.itacademy.tatabakach.transportcompany.web.dto.PaymentDTO;

@Component
public class PaymentFromDTOConverter implements Function<PaymentDTO, IPayment> {

	@Autowired
	private IPaymentService paymentService;
	
	@Autowired
	private IOrderService orderService;

	@Autowired
	private ICompanyService companyService;

	@Override
	public IPayment apply(final PaymentDTO dto) {
		final IPayment entity = paymentService.createEntity();
		entity.setId(dto.getId());
		entity.setDate(dto.getDate());
		
		Integer orderId = dto.getOrderId();
		if (orderId != null) {
			IOrder o = orderService.createEntity();
			o.setId(orderId);
			entity.setOrder(o);
		}
		
		Integer companyId = dto.getCompanyId();
		if (companyId != null) {
			ICompany c = companyService.createEntity();
			c.setId(companyId);
			entity.setCompany(c);
		}
		
		
		entity.setCurrency(dto.getCurrency());
		entity.setRate(dto.getRate());
		entity.setAmount(dto.getAmount());
		entity.setNote(dto.getNote());

		return entity;
	}
}
