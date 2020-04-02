package by.itacademy.dzhivushko.cars.web.converter;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.itacademy.dzhivushko.cars.dao.api.entity.table.IBrand;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IEngine;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IModel;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IModelInfo;
import by.itacademy.dzhivushko.cars.service.IBrandService;
import by.itacademy.dzhivushko.cars.service.IEngineService;
import by.itacademy.dzhivushko.cars.service.IModelService;
import by.itacademy.dzhivushko.cars.web.dto.ModelDTO;

@Component
public class ModelFromDTOConverter implements Function<ModelDTO, IModel> {

    @Autowired
    private IModelService modelService;
    @Autowired
    private IBrandService brandService;
    @Autowired
    private IEngineService engineService;

    @Override
    public IModel apply(final ModelDTO dto) {
        final IModel entity = modelService.createEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());

        final IBrand brand = brandService.createEntity();
        brand.setId(dto.getBrandId());
        entity.setBrand(brand);

        final Set<Integer> engineIds = dto.getEngineIds();
        if (CollectionUtils.isNotEmpty(engineIds)) {
            entity.setEngines(engineIds.stream().map((id) -> {
                final IEngine engine = engineService.createEntity();
                engine.setId(id);
                return engine;
            }).collect(Collectors.toSet()));
        }

        final IModelInfo infoEntity = modelService.createInfoEntity();
        infoEntity.setId(dto.getId());
        infoEntity.setDescription(dto.getInfo().getDescription());
        entity.setModelInfo(infoEntity);

        return entity;
    }
}
