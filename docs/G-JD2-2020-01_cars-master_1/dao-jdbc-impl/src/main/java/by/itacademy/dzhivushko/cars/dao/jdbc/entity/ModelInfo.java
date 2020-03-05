package by.itacademy.dzhivushko.cars.dao.jdbc.entity;

import by.itacademy.dzhivushko.cars.dao.api.entity.table.IModel;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IModelInfo;

public class ModelInfo extends BaseEntity implements IModelInfo {
    private String description;

    private IModel model;

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public IModel getModel() {
        return model;
    }

    @Override
    public void setModel(final IModel model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "ModelInfo [description=" + description + ", getId()=" + getId() + "]";
    }

}
