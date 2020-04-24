package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderRewardPercent;
import by.itacademy.tatabakach.transportcompany.service.IOrderRewardPercentService;
import by.itacademy.tatabakach.transportcompany.web.dto.OrderRewardPercentDTO;

@Component
public class OrderRewardPercentFromDTOConverter implements Function<OrderRewardPercentDTO, IOrderRewardPercent> {

	@Autowired
	private IOrderRewardPercentService orderRewardPercentService;

	@Override
	public IOrderRewardPercent apply(final OrderRewardPercentDTO dto) {
		final IOrderRewardPercent entity = orderRewardPercentService.createEntity();
		entity.setId(dto.getId());
		entity.setPercent(dto.getPercent());
		return entity;
	}
}
