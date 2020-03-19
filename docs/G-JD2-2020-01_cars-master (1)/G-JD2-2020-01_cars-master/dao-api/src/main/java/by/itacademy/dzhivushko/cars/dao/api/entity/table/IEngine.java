package by.itacademy.dzhivushko.cars.dao.api.entity.table;

import by.itacademy.dzhivushko.cars.dao.api.entity.enums.EngineType;

public interface IEngine extends IBaseEntity {

    EngineType getType();

    String getTitle();

    Integer getVolume();

    void setType(EngineType type);

    void setTitle(String title);

    void setVolume(Integer volume);

}
