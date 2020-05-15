package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IAddress;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRouteItem;
import by.itacademy.tatabakach.transportcompany.service.IAddressService;
import by.itacademy.tatabakach.transportcompany.service.IOrderService;
import by.itacademy.tatabakach.transportcompany.service.IRouteItemService;
import by.itacademy.tatabakach.transportcompany.web.dto.RouteItemDTO;

@Component
public class RouteItemFromDTOConverter implements Function<RouteItemDTO, IRouteItem> {

	@Autowired
	private IRouteItemService routeItemService;
	
	@Autowired
	private IOrderService orderService;

	@Autowired
	private IAddressService addressService;

	@Override
	public IRouteItem apply(final RouteItemDTO dto) {
		final IRouteItem entity = routeItemService.createEntity();
		entity.setId(dto.getId());

		Integer orderId = dto.getOrderId();
		if (orderId != null) {
			IOrder o = orderService.createEntity();
			o.setId(orderId);
			entity.setOrder(o);
		}
		
		Integer addressId = dto.getAddressId();
		if (addressId != null) {
			IAddress a = addressService.createEntity();
			a.setId(addressId);
			entity.setAddress(a);
		}
		
		entity.setDate(dto.getDate());
		entity.setCargoWeight(dto.getCargoWeight());
		entity.setCargoVolume(dto.getCargoVolume());
		
		Integer custom = dto.getCustomId();
		if (custom != null) {
			IAddress c = addressService.createEntity();
			c.setId(custom);
			entity.setCustom(c);
		}
		
		entity.setContactPerson(dto.getContactPerson());
		entity.setContactPhone(dto.getContactPhone());
		entity.setNote(dto.getNote());

		return entity;
	}
}
