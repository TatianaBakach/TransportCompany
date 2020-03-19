package by.itacademy.dzhivushko.cars.dao.jdbc.entity;

import java.util.Date;

import by.itacademy.dzhivushko.cars.dao.api.entity.table.ICar;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IModel;

public class Car extends BaseEntity implements ICar {

    private String vin;

    private IModel model;

    private Boolean sold;

    private Date soldDate;

    @Override
    public String getVin() {
        return vin;
    }

    @Override
    public void setVin(final String vin) {
        this.vin = vin;
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
    public Boolean getSold() {
        return sold;
    }

    @Override
    public void setSold(final Boolean sold) {
        this.sold = sold;
    }

    @Override
    public Date getSoldDate() {
        return soldDate;
    }

    @Override
    public void setSoldDate(final Date soldDate) {
        this.soldDate = soldDate;
    }

    @Override
    public Integer getVersion() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setVersion(Integer version) {
        // TODO Auto-generated method stub

    }

}
