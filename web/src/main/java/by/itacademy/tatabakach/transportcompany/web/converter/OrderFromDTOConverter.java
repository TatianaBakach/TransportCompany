package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IAddress;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ILocality;
import by.itacademy.tatabakach.transportcompany.service.IAddressService;
import by.itacademy.tatabakach.transportcompany.service.ILocalityService;
import by.itacademy.tatabakach.transportcompany.web.dto.AddressDTO;

@Component
public class OrderFromDTOConverter implements Function<AddressDTO, IAddress> {

	@Autowired
	private IAddressService addressService;

	@Autowired
	private ILocalityService localityService;

	@Override
	public IAddress apply(final AddressDTO dto) {
		final IAddress entity = addressService.createEntity();
		entity.setId(dto.getId());
		entity.setPostcode(dto.getPostcode());
		entity.setExactAddress(dto.getExactAddress());
		entity.setNote(dto.getNote());

		Integer localityId = dto.getLocalityId();
		if (localityId != null) {
			ILocality l = localityService.createEntity();
			l.setId(localityId);
			entity.setLocality(l);
		}

		return entity;
	}
}
