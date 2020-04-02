package by.itacademy.dzhivushko.cars.web.dto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EngineDTO {

    private Integer id;

    @Size(min = 1, max = 50)
    private String title;
    @NotNull
    private String type;
    @Min(1)
    private Integer volume;

    private Date created;

    private Date updated;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(final Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(final Date updated) {
        this.updated = updated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(final Integer volume) {
        this.volume = volume;
    }

}
