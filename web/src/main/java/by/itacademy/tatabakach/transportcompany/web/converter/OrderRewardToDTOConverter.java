package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderReward;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderRewardPercent;
import by.itacademy.tatabakach.transportcompany.web.dto.OrderRewardDTO;

@Component
public class OrderRewardToDTOConverter implements Function<IOrderReward, OrderRewardDTO> {

	@Override
	public OrderRewardDTO apply(final IOrderReward entity) {
		final OrderRewardDTO dto = new OrderRewardDTO();
		dto.setId(entity.getId());

		IOrder order = entity.getOrder();
		if (order != null) {
			dto.setOrderName(order.getNumber());
			dto.setOrderId(order.getId());
		}

		IEmployee employee = entity.getEmployee();
		if (employee != null) {
			dto.setEmployeeName(String.format("%s %s", employee.getLastName(), employee.getFirstName()));
			dto.setEmployeeId(employee.getId());
		}

		dto.setRewardType(entity.getRewardType());

		IOrderRewardPercent orderRewardPercent = entity.getOrderRewardPercent();
		if (orderRewardPercent != null) {
			dto.setOrderRewardPercentName(orderRewardPercent.getName());
			dto.setOrderRewardPercentId(orderRewardPercent.getId());
		}

		dto.setAdditionalExpenses(entity.getAdditionalExpenses());
		dto.setAmount(entity.getAmount());
		dto.setPaymentDate(entity.getPaymentDate());
		dto.setRewardIssued(entity.getRewardIssued());
		dto.setNote(entity.getNote());

		return dto;
	}

}
