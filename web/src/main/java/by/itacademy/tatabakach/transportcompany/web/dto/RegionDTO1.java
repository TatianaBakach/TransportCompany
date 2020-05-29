package by.itacademy.tatabakach.transportcompany.web.dto;
public class RegionDTO1 {
    private Integer id;
    private String title;

    public RegionDTO1(final Integer id, final String title) {
        super();
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

}