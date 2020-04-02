package by.itacademy.dzhivushko.cars.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.itacademy.dzhivushko.cars.dao.api.entity.enums.EngineType;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IEngine;
import by.itacademy.dzhivushko.cars.service.IEngineService;
import by.itacademy.dzhivushko.cars.web.dto.EngineDTO;

@Component
public class EngineFromDTOConverter implements Function<EngineDTO, IEngine> {

    @Autowired
    private IEngineService engineService;

    @Override
    public IEngine apply(final EngineDTO dto) {
        final IEngine entity = engineService.createEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setType(EngineType.valueOf(dto.getType()));
        entity.setVolume(dto.getVolume());
        return entity;
    }
}
