package by.itacademy.dzhivushko.cars.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.stereotype.Repository;

import by.itacademy.dzhivushko.cars.dao.api.IModelInfoDao;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IModelInfo;
import by.itacademy.dzhivushko.cars.dao.jdbc.entity.ModelInfo;
import by.itacademy.dzhivushko.cars.dao.jdbc.util.PreparedStatementAction;
@Repository
public class ModelInfoDaoImpl extends AbstractDaoImpl<IModelInfo, Integer> implements IModelInfoDao {

    @Override
    public IModelInfo createEntity() {
        return new ModelInfo();
    }

    @Override
    public void insert(final IModelInfo entity) {
        executeStatement(new PreparedStatementAction<IModelInfo>(String
                .format("insert into %s (id, description,created, updated) " + "values(?,?,?,?)", getTableName())) {
            @Override
            public IModelInfo doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
                pStmt.setInt(1, entity.getId());
                pStmt.setString(2, entity.getDescription());
                pStmt.setObject(3, entity.getCreated(), Types.TIMESTAMP);
                pStmt.setObject(4, entity.getUpdated(), Types.TIMESTAMP);
                pStmt.executeUpdate();
                return entity;
            }
        });
    }

    @Override
    public void update(final IModelInfo entity) {
        executeStatement(new PreparedStatementAction<IModelInfo>(
                String.format("update %s set description=?, updated=? where id=?", getTableName())) {
            @Override
            public IModelInfo doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
                pStmt.setString(1, entity.getDescription());
                pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
                pStmt.setInt(3, entity.getId());

                pStmt.executeUpdate();
                return entity;
            }
        });
    }

    @Override
    protected IModelInfo parseRow(final ResultSet resultSet) throws SQLException {
        final IModelInfo entity = createEntity();
        entity.setId((Integer) resultSet.getObject("id"));
        entity.setDescription(resultSet.getString("description"));
        entity.setCreated(resultSet.getTimestamp("created"));
        entity.setUpdated(resultSet.getTimestamp("updated"));
        return entity;
    }

    @Override
    protected String getTableName() {
        return "model_info";
    }

}
