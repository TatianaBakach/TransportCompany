package by.itacademy.dzhivushko.cars.web.converter;

import java.util.Calendar;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.itacademy.dzhivushko.cars.dao.api.entity.table.ICar;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IModel;
import by.itacademy.dzhivushko.cars.service.ICarService;
import by.itacademy.dzhivushko.cars.service.IModelService;
import by.itacademy.dzhivushko.cars.web.dto.CarDTO;

@Component
public class CarFromDTOConverter implements Function<CarDTO, ICar> {

	@Autowired
	private ICarService carService;
	@Autowired
	private IModelService modelService;

	@Override
	public ICar apply(final CarDTO dto) {
		final ICar entity = carService.createEntity();
		entity.setId(dto.getId());
		entity.setVin(dto.getVin());
		entity.setSold(dto.getSold());
		final Date soldDate = dto.getSoldDate();
		if (soldDate != null) {
			final Calendar fullDateCalendar = Calendar.getInstance();
			fullDateCalendar.setTime(soldDate);

			final Date soldTime = dto.getSoldTime();
			if (soldTime != null) {
				final Calendar timeCalendar = Calendar.getInstance();
				timeCalendar.setTime(soldTime);
				fullDateCalendar.set(Calendar.HOUR_OF_DAY, timeCalendar.get(Calendar.HOUR_OF_DAY));
				fullDateCalendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));
			}

			entity.setSoldDate(fullDateCalendar.getTime());
		}

		final IModel model = modelService.createEntity();
		model.setId(dto.getModelId());
		entity.setModel(model);

		return entity;
	}
}
