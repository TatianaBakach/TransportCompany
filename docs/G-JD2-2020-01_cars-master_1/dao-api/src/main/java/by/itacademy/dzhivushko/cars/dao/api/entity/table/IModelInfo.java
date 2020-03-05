package by.itacademy.dzhivushko.cars.dao.api.entity.table;

public interface IModelInfo extends IBaseEntity {

    String getDescription();

    void setDescription(String description);

    IModel getModel();

    void setModel(IModel model);

}
