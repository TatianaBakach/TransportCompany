package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IAddress;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ILocality;
import by.itacademy.tatabakach.transportcompany.web.dto.AddressDTO;

@Component
public class AddressToDTOConverter implements Function<IAddress, AddressDTO> {

	@Override
	public AddressDTO apply(final IAddress entity) {
		final AddressDTO dto = new AddressDTO();
		dto.setId(entity.getId());
		dto.setPostcode(entity.getPostcode());
		dto.setExactAddress(entity.getExactAddress());
		dto.setNote(entity.getNote());

		ILocality locality = entity.getLocality();
		if (locality != null) {
			dto.setLocalityName(locality.getName());
			dto.setLocalityId(locality.getId());
		}

		return dto;
	}

}
