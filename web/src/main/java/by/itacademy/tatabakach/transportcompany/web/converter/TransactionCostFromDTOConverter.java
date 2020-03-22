package by.itacademy.tatabakach.transportcompany.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICar;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITransactionCost;
import by.itacademy.tatabakach.transportcompany.service.ICarService;
import by.itacademy.tatabakach.transportcompany.service.ITransactionCostService;
import by.itacademy.tatabakach.transportcompany.web.dto.CarDTO;
import by.itacademy.tatabakach.transportcompany.web.dto.TransactionCostDTO;


@Component
public class TransactionCostFromDTOConverter implements Function<TransactionCostDTO, ITransactionCost> {

	@Autowired
	private ITransactionCostService transactionCostService;

	@Override
	public ITransactionCost apply(final TransactionCostDTO dto) {
		final ITransactionCost entity = transactionCostService.createEntity();
		entity.setId(dto.getId());
		entity.setDate(dto.getDate());
		entity.setCurrency(dto.getCurrency());
		entity.setAmount(dto.getAmount());
		entity.setRate(dto.getRate());
		entity.setIntermediateCurrency(dto.getIntermediateCurrency());
		entity.setIntermediateCurrencyRate(dto.getIntermediateCurrencyRate());
		entity.setPaymentPeriod(dto.getPaymentPeriod());
		entity.setPaymentTermsType(dto.getPaymentTermsType());
		entity.setNote(dto.getNote());
		return entity;
	}
}
