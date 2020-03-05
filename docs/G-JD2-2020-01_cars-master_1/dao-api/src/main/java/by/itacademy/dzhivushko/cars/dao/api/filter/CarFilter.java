package by.itacademy.dzhivushko.cars.dao.api.filter;

public class CarFilter extends AbstractFilter {

    private Integer loggedUserId;

    private String vin;

    private Boolean sold;

    public Integer getLoggedUserId() {
        return loggedUserId;
    }

    public void setLoggedUserId(Integer loggedUserId) {
        this.loggedUserId = loggedUserId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(final String vin) {
        this.vin = vin;
    }

    public Boolean getSold() {
        return sold;
    }

    public void setSold(final Boolean sold) {
        this.sold = sold;
    }
}
