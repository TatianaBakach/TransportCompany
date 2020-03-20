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

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Car;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Car_;
import by.itacademy.tatabakach.transportcompany.daoapi.ICarDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICar;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CarFilter;

@Repository
public class CarDaoImpl extends AbstractDaoImpl<ICar, Integer> implements ICarDao {

	protected CarDaoImpl() {
		super(Car.class);
	}

	@Override
	public ICar createEntity() {
		return new Car();
	}

	@Override
	public long getCount(final CarFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define
																	// type of result
		final Root<Car> from = cq.from(Car.class); // select from brand
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

	@Override
	public List<ICar> find(final CarFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ICar> cq = cb.createQuery(ICar.class); // define
																		// type
																		// of
		// result
		final Root<Car> from = cq.from(Car.class);// select from brand
		cq.select(from); // select what? select *

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super Car, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); // build path to
																// sort
			// property
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order
																			// by
			// column_name
			// order
		}

		final TypedQuery<ICar> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private SingularAttribute<? super Car, ?> toMetamodelFormat(final String sortColumn) {
		switch (sortColumn) {
		case "id":
			return Car_.id;
		case "model":
			return Car_.model;
		case "number":
			return Car_.number;
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

}
