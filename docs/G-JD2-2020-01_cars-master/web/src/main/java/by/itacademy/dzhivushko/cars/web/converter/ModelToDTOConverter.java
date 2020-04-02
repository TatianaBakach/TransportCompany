package by.itacademy.dzhivushko.cars.web.converter;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import by.itacademy.dzhivushko.cars.dao.api.entity.table.IBrand;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IEngine;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IModel;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IModelInfo;
import by.itacademy.dzhivushko.cars.web.dto.ModelDTO;

@Component
public class ModelToDTOConverter implements Function<IModel, ModelDTO> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModelToDTOConverter.class);

    @Override
    public ModelDTO apply(final IModel entity) {
        final ModelDTO modelDto = new ModelDTO();
        modelDto.setId(entity.getId());
        modelDto.setName(entity.getName());
        modelDto.setCreated(entity.getCreated());
        modelDto.setUpdated(entity.getUpdated());

        final IBrand brand = entity.getBrand();
        if (brand != null) {
            modelDto.setBrandId(brand.getId());
            modelDto.setBrandName(brand.getName());
        }

        try {
            final Set<IEngine> engines = entity.getEngines();
            if (engines != null) {
                modelDto.setEngineIds(engines.stream().map(IEngine::getId).collect(Collectors.toSet()));
            }
        } catch (final Exception e) {
            LOGGER.warn("ignore conversion of 'engines' collection because of:" + e.getMessage());
        }

        final IModelInfo modelInfo = entity.getModelInfo();
        if (modelInfo != null) {
            modelDto.getInfo().setDescription(modelInfo.getDescription());
        }

        return modelDto;
    }

}
