package by.itacademy.tatabakach.transportcompany.daojdbcimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.daoapi.IVatDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IVat;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.VatFilter;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.entity.Vat;
import by.itacademy.tatabakach.transportcompany.daojdbcimpl.util.PreparedStatementAction;

@Repository
public class VatDaoImpl extends AbstractDaoImpl<IVat, Integer> implements IVatDao {

	@Override
	public IVat createEntity() {
		return new Vat();
	}

	@Override
	public void insert(final IVat entity) {
		executeStatement(new PreparedStatementAction<IVat>(
				String.format("insert into %s (name, rate) values(?,?)", getTableName()), true) {
			@Override
			public IVat doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setBigDecimal(2, entity.getRate());

				pStmt.executeUpdate();

				final ResultSet rs = pStmt.getGeneratedKeys();
				rs.next();
				final int id = rs.getInt("id");

				rs.close();

				entity.setId(id);
				return entity;
			}
		});

	}

	@Override
	public void update(final IVat entity) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public List<IVat> find(final VatFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	public long getCount(final VatFilter filter) {
		throw new RuntimeException("will be implemented in ORM layer. Too complex for plain jdbc ");
	}

	@Override
	protected IVat parseRow(final ResultSet resultSet) throws SQLException {
		final IVat entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setRate(resultSet.getBigDecimal("rate"));
		return entity;
	}

	@Override
	protected String getTableName() {
		return "vat";
	}

}
