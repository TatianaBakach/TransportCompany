package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IPayment;
import by.itacademy.tatabakach.transportcompany.web.dto.PaymentDTO;

@Component
public class PaymentToDTOConverter implements Function<IPayment, PaymentDTO> {

	@Override
	public PaymentDTO apply(final IPayment entity) {
		final PaymentDTO dto = new PaymentDTO();
		dto.setId(entity.getId());
		dto.setDate(entity.getDate());
		
		IOrder order = entity.getOrder();
		if (order != null) {
			dto.setOrderName(order.getNumber());
			dto.setOrderId(order.getId());
		}

		ICompany company = entity.getCompany();
		if (company != null) {
			dto.setCompanyName(company.getName());
			dto.setCompanyId(company.getId());
		}
		
		dto.setCurrency(entity.getCurrency());
		dto.setRate(entity.getRate());
		dto.setAmount(entity.getAmount());
		dto.setNote(entity.getNote());

		return dto;
	}

}
