package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICorrespondence;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.web.dto.CorrespondenceDTO;

@Component
public class CorrespondenceToDTOConverter implements Function<ICorrespondence, CorrespondenceDTO> {

	@Override
	public CorrespondenceDTO apply(final ICorrespondence entity) {
		final CorrespondenceDTO dto = new CorrespondenceDTO();
		dto.setId(entity.getId());
		dto.setCorrespondenceType(entity.getCorrespondenceType());
		
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
		
		dto.setDate(entity.getDate());
		dto.setContent(entity.getContent());
		dto.setNote(entity.getNote());

		return dto;
	}

}
