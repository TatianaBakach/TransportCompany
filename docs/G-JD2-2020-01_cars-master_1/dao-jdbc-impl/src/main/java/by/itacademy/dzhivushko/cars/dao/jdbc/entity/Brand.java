package by.itacademy.dzhivushko.cars.dao.jdbc.entity;

import by.itacademy.dzhivushko.cars.dao.api.entity.table.IBrand;

public class Brand extends BaseEntity implements IBrand {

    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Brand [name=" + name + ", getId()=" + getId() + ", getCreated()=" + getCreated() + ", getUpdated()=" + getUpdated() + "]";
    }

}
