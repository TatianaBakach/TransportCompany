package by.itacademy.tatabakach.transportcompany.web.converter;

import java.sql.Types;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.TransactionCost_;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICar;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITransactionCost;
import by.itacademy.tatabakach.transportcompany.web.dto.CarDTO;
import by.itacademy.tatabakach.transportcompany.web.dto.TransactionCostDTO;

@Component
public class TransactionCostToDTOConverter implements Function<ITransactionCost, TransactionCostDTO> {
	
	@Override
	public TransactionCostDTO apply(final ITransactionCost entity) {
		final TransactionCostDTO dto = new TransactionCostDTO();
		dto.setId(entity.getId());
		dto.setDate(entity.getDate());
		dto.setCurrency(entity.getCurrency());
		dto.setAmount(entity.getAmount());
		dto.setRate(entity.getRate());
		dto.setIntermediateCurrency(entity.getIntermediateCurrency());
		dto.setIntermediateCurrencyRate(entity.getIntermediateCurrencyRate());
		dto.setPaymentPeriod(entity.getPaymentPeriod());
		dto.setPaymentTermsType(entity.getPaymentTermsType());
		dto.setNote(entity.getNote());

		return dto;
	}

}
