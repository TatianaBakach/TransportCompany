package by.itacademy.tatabakach.transportcompany.dao.orm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.OrderRewardPercent;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.OrderRewardPercent_;
import by.itacademy.tatabakach.transportcompany.daoapi.IOrderRewardPercentDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderRewardPercent;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.OrderRewardPercentFilter;

@Repository
public class OrderRewardPercentDaoImpl extends AbstractDaoImpl<IOrderRewardPercent, Integer>
		implements IOrderRewardPercentDao {

	protected OrderRewardPercentDaoImpl() {
		super(OrderRewardPercent.class);
	}

	@Override
	public IOrderRewardPercent createEntity() {
		return new OrderRewardPercent();
	}

	@Override
	public long getCount(final OrderRewardPercentFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define
																	// type of result
		final Root<OrderRewardPercent> from = cq.from(OrderRewardPercent.class); // select from brand
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

	@Override
	public List<IOrderRewardPercent> find(final OrderRewardPercentFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IOrderRewardPercent> cq = cb.createQuery(IOrderRewardPercent.class); // define
																		// type
																		// of
		// result
		final Root<OrderRewardPercent> from = cq.from(OrderRewardPercent.class);// select from brand
		cq.select(from); // select what? select *

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super OrderRewardPercent, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); // build path to
																// sort
			// property
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order
																			// by
			// column_name
			// order
		}

		final TypedQuery<IOrderRewardPercent> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private SingularAttribute<? super OrderRewardPercent, ?> toMetamodelFormat(final String sortColumn) {
		switch (sortColumn) {
		case "id":
			return OrderRewardPercent_.id;
		case "percent":
			return OrderRewardPercent_.percent;
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}
}
