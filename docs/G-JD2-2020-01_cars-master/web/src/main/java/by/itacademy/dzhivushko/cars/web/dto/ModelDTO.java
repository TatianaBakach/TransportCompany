package by.itacademy.dzhivushko.cars.web.dto;

import java.util.Date;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ModelDTO {

    private Integer id;

    @Size(min = 1, max = 50)
    private String name;

    @NotNull
    private Integer brandId;

    private String brandName;

    private Set<Integer> engineIds;

    private Date created;

    private Date updated;

    @NotNull
    @Valid
    private ModelInfoDTO info = new ModelInfoDTO();

    public ModelInfoDTO getInfo() {
        return info;
    }

    public void setInfo(final ModelInfoDTO info) {
        this.info = info;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(final String brandName) {
        this.brandName = brandName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(final Integer brandId) {
        this.brandId = brandId;
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

    public Set<Integer> getEngineIds() {
        return engineIds;
    }

    public void setEngineIds(final Set<Integer> engineIds) {
        this.engineIds = engineIds;
    }
}
