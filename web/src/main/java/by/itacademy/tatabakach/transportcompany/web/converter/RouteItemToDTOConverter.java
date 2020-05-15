package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IAddress;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRouteItem;
import by.itacademy.tatabakach.transportcompany.web.dto.RouteItemDTO;

@Component
public class RouteItemToDTOConverter implements Function<IRouteItem, RouteItemDTO> {

	@Override
	public RouteItemDTO apply(final IRouteItem entity) {
		final RouteItemDTO dto = new RouteItemDTO();
		dto.setId(entity.getId());
		
		IOrder order = entity.getOrder();
		if (order != null) {
			dto.setOrderName(order.getNumber());
			dto.setOrderId(order.getId());
		}

		IAddress address = entity.getAddress();
		if (address != null) {
			dto.setAddressName(String.format("%s %s %s", address.getPostcode(), address.getLocality().getName(), address.getExactAddress()));
			dto.setAddressId(address.getId());
		}
		
		dto.setDate(entity.getDate());
		dto.setCargoWeight(entity.getCargoWeight());
		dto.setCargoVolume(entity.getCargoVolume());
		
		IAddress custom = entity.getCustom();
		if (custom != null) {
			dto.setCustomName(String.format("%s %s %s", custom.getPostcode(), custom.getLocality().getName(), custom.getExactAddress()));
			dto.setCustomId(custom.getId());
		}
		
		dto.setContactPerson(entity.getContactPerson());
		dto.setContactPhone(entity.getContactPhone());
		dto.setNote(entity.getNote());

		return dto;
	}

}
