package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderReward;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderRewardPercent;
import by.itacademy.tatabakach.transportcompany.service.IEmployeeService;
import by.itacademy.tatabakach.transportcompany.service.IOrderRewardPercentService;
import by.itacademy.tatabakach.transportcompany.service.IOrderRewardService;
import by.itacademy.tatabakach.transportcompany.service.IOrderService;
import by.itacademy.tatabakach.transportcompany.web.dto.OrderRewardDTO;

@Component
public class OrderRewardFromDTOConverter implements Function<OrderRewardDTO, IOrderReward> {

	@Autowired
	private IOrderRewardService orderRewardService;
	
	@Autowired
	private IOrderService orderService;

	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private IOrderRewardPercentService orderRewardPercentService;

	@Override
	public IOrderReward apply(final OrderRewardDTO dto) {
		final IOrderReward entity = orderRewardService.createEntity();
		entity.setId(dto.getId());
		
		Integer orderId = dto.getOrderId();
		if (orderId != null) {
			IOrder o = orderService.createEntity();
			o.setId(orderId);
			entity.setOrder(o);
		}
		
		Integer employeeId = dto.getEmployeeId();
		if (employeeId != null) {
			IEmployee e = employeeService.createEntity();
			e.setId(employeeId);
			entity.setEmployee(e);
		}
		
		entity.setRewardType(dto.getRewardType());

		Integer orderRewardPercentId = dto.getOrderRewardPercentId();
		if (orderRewardPercentId != null) {
			IOrderRewardPercent p = orderRewardPercentService.createEntity();
			p.setId(orderRewardPercentId);
			entity.setOrderRewardPercent(p);;
		}
		
		entity.setAdditionalExpenses(dto.getAdditionalExpenses());
		entity.setAmount(dto.getAmount());
		entity.setPaymentDate(dto.getPaymentDate());
		entity.setRewardIssued(dto.getRewardIssued());
		entity.setNote(dto.getNote());

		return entity;
	}
}
