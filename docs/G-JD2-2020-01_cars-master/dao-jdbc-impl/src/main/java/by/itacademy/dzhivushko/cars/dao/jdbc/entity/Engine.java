package by.itacademy.dzhivushko.cars.dao.jdbc.entity;

import by.itacademy.dzhivushko.cars.dao.api.entity.enums.EngineType;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IEngine;

public class Engine extends BaseEntity implements IEngine {
    private EngineType type;

    private String title;

    private Integer volume;

    @Override
    public EngineType getType() {
        return type;
    }

    @Override
    public void setType(final EngineType type) {
        this.type = type;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(final String title) {
        this.title = title;
    }

    @Override
    public Integer getVolume() {
        return volume;
    }

    @Override
    public void setVolume(final Integer volume) {
        this.volume = volume;
    }

}
