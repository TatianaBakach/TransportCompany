package by.itacademy.dzhivushko.cars.web.dto.search;

public class CarSearchDTO {

    private String vin;

    private boolean freeOnly;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Boolean getFreeOnly() {
        return freeOnly;
    }

    public void setFreeOnly(Boolean freeOnly) {
        this.freeOnly = freeOnly;
    }

}
