package by.itacademy.dzhivushko.cars.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.dzhivushko.cars.dao.api.entity.table.IEngine;
import by.itacademy.dzhivushko.cars.web.dto.EngineDTO;

@Component
public class EngineToDTOConverter implements Function<IEngine, EngineDTO> {

    @Override
    public EngineDTO apply(final IEngine entity) {
        final EngineDTO dto = new EngineDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setVolume(entity.getVolume());
        dto.setType(entity.getType().name());
        dto.setCreated(entity.getCreated());
        dto.setUpdated(entity.getUpdated());
        return dto;
    }

}
