package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICorrespondence;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.service.ICompanyService;
import by.itacademy.tatabakach.transportcompany.service.ICorrespondenceService;
import by.itacademy.tatabakach.transportcompany.service.IOrderService;
import by.itacademy.tatabakach.transportcompany.web.dto.CorrespondenceDTO;

@Component
public class CorrespondenceFromDTOConverter implements Function<CorrespondenceDTO, ICorrespondence> {

	@Autowired
	private ICorrespondenceService correspondenceService;
	
	@Autowired
	private IOrderService orderService;

	@Autowired
	private ICompanyService companyService;

	@Override
	public ICorrespondence apply(final CorrespondenceDTO dto) {
		final ICorrespondence entity = correspondenceService.createEntity();
		entity.setId(dto.getId());
		entity.setCorrespondenceType(dto.getCorrespondenceType());

		Integer orderId = dto.getOrderId();
		if (orderId != null) {
			IOrder o = orderService.createEntity();
			o.setId(orderId);
			entity.setOrder(o);
		}
		
		Integer companyId = dto.getCompanyId();
		if (companyId != null) {
			ICompany c = companyService.createEntity();
			c.setId(companyId);
			entity.setCompany(c);
		}
		
		entity.setDate(dto.getDate());
		entity.setContent(dto.getContent());
		entity.setNote(dto.getNote());

		return entity;
	}
}
