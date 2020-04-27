package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderRewardPercent;
import by.itacademy.tatabakach.transportcompany.web.dto.OrderRewardPercentDTO;

@Component
public class OrderRewardPercentToDTOConverter implements Function<IOrderRewardPercent, OrderRewardPercentDTO> {
	
	@Override
	public OrderRewardPercentDTO apply(final IOrderRewardPercent entity) {
		final OrderRewardPercentDTO dto = new OrderRewardPercentDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setPercent(entity.getPercent());
		return dto;
	}

}
