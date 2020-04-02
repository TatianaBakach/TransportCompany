package by.itacademy.dzhivushko.cars.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.itacademy.dzhivushko.cars.dao.api.IEngineDao;
import by.itacademy.dzhivushko.cars.dao.api.IModelDao;
import by.itacademy.dzhivushko.cars.dao.api.IModelInfoDao;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IEngine;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IModel;
import by.itacademy.dzhivushko.cars.dao.api.filter.ModelFilter;
import by.itacademy.dzhivushko.cars.dao.jdbc.entity.Brand;
import by.itacademy.dzhivushko.cars.dao.jdbc.entity.Model;
import by.itacademy.dzhivushko.cars.dao.jdbc.util.SQLExecutionException;
@Repository
public class ModelDaoImpl extends AbstractDaoImpl<IModel, Integer> implements IModelDao {

	@Autowired
	private IModelInfoDao modelInfoDao;
	@Autowired
	private IEngineDao engineDao;

	@Override
	public IModel createEntity() {
		return new Model();
	}

	@Override
	public void insert(final IModel entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String
						.format("insert into %s (name, created, updated, brand_id) values(?,?,?,?)", getTableName()),
						Statement.RETURN_GENERATED_KEYS)) {
			c.setAutoCommit(false);
			try {
				pStmt.setString(1, entity.getName());
				pStmt.setObject(2, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(4, entity.getBrand().getId());

				pStmt.executeUpdate();

				final ResultSet rs = pStmt.getGeneratedKeys();
				rs.next();
				final int id = rs.getInt("id");

				rs.close();
				entity.setId(id);
				// the same should be done in 'update' DAO method
				updateEngines(entity, c);
				c.commit();
			} catch (final Exception e) {
				c.rollback();
				throw new RuntimeException(e);
			}

		} catch (final SQLException e) {
			throw new SQLExecutionException(e);
		}
	}

	@Override
	public void update(final IModel entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(
						String.format("update %s set name=?, updated=?, brand_id=? where id=?", getTableName()))) {
			c.setAutoCommit(false);
			try {
				pStmt.setString(1, entity.getName());
				pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(3, entity.getBrand().getId());
				pStmt.setInt(4, entity.getId());
				pStmt.executeUpdate();
				// the same should be done in 'update' DAO method
				updateEngines(entity, c);
				c.commit();
			} catch (final Exception e) {
				c.rollback();
				throw new RuntimeException(e);
			}

		} catch (final SQLException e) {
			throw new SQLExecutionException(e);
		}
	}

	@Override
	protected IModel parseRow(final ResultSet resultSet, final Set<String> columns) throws SQLException {
		final IModel entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final Integer brandId = (Integer) resultSet.getObject("brand_id");
		if (brandId != null) {
			final Brand brand = new Brand();
			brand.setId(brandId);
			entity.setBrand(brand);
		}
		return entity;
	}

	@Override
	public void deleteAll() {
		try (Connection c = getConnection();
				PreparedStatement deleteEngineRefsStmt = c.prepareStatement("delete from model_2_engine");
				PreparedStatement deleteAllStmt = c.prepareStatement("delete from " + getTableName());) {
			c.setAutoCommit(false);
			try {
				deleteEngineRefsStmt.executeUpdate();
				deleteAllStmt.executeUpdate();

				deleteEngineRefsStmt.close();
				deleteAllStmt.close();

				c.commit();
			} catch (final Exception e) {
				c.rollback();
				throw new RuntimeException(e);
			}

		} catch (final SQLException e) {
			throw new SQLExecutionException(e);
		}

	}

	@Override
	public IModel getFullInfo(final Integer id) {
		final IModel model = get(id);
		final Set<IEngine> engines = engineDao.getByModel(id);
		model.setEngines(engines);

		model.setModelInfo(modelInfoDao.get(id));
		return model;
	}

	@Override
	protected String getTableName() {
		return "model";
	}

	private void updateEngines(final IModel entity, final Connection c) throws SQLException {
		// clear all existing records related to the current model
		final PreparedStatement deleteStmt = c.prepareStatement("delete from model_2_engine where model_id=?");
		deleteStmt.setInt(1, entity.getId());
		deleteStmt.executeUpdate();
		deleteStmt.close();

		if (entity.getEngines().isEmpty()) {
			return;
		}

		// insert actual list of records using 'batch'
		final PreparedStatement pStmt = c
				.prepareStatement("insert into model_2_engine (model_id, engine_id) values(?,?)");

		for (final IEngine e : entity.getEngines()) {
			pStmt.setInt(1, entity.getId());
			pStmt.setInt(2, e.getId());
			pStmt.addBatch();
		}
		pStmt.executeBatch();
		pStmt.close();
	}

	@Override
	public List<IModel> find(final ModelFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final ModelFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}
}
