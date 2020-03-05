package by.itacademy.dzhivushko.cars.dao.jdbc.entity;

import java.util.HashSet;
import java.util.Set;

import by.itacademy.dzhivushko.cars.dao.api.entity.table.IBrand;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IEngine;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IModel;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IModelInfo;

public class Model extends BaseEntity implements IModel {

    private String name;

    private IBrand brand;

    private IModelInfo modelInfo;

    private Set<IEngine> engines = new HashSet<>();


    public IModelInfo getModelInfo() {
        return modelInfo;
    }

    public void setModelInfo(IModelInfo modelInfo) {
        this.modelInfo = modelInfo;
    }

    public Set<IEngine> getEngines() {
        return engines;
    }

    public void setEngines(Set<IEngine> engines) {
        this.engines = engines;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public IBrand getBrand() {
        return brand;
    }

    @Override
    public void setBrand(final IBrand brand) {
        this.brand = brand;
    }


    @Override
    public String toString() {
        return "Model [name=" + name + ", getId()=" + getId() + "]";
    }
}
