package by.itacademy.dzhivushko.cars.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import by.itacademy.dzhivushko.cars.dao.api.entity.table.IBrand;

@Entity
public class Brand extends BaseEntity implements IBrand {

    @Column // simple column
    private String name;

    @Transient // skip from DB mapping
    private String upperCaseName;

    public Brand(String name, Integer id) {
        super();
        setId(id);
        this.name = name;
    }

    public Brand() {
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(final String name) {
        this.name = name;
        setUpperCaseName(name.toUpperCase());
    }

    public String getUpperCaseName() {
        return upperCaseName;
    }

    public void setUpperCaseName(final String upperCaseName) {
        this.upperCaseName = upperCaseName;
    }
}
