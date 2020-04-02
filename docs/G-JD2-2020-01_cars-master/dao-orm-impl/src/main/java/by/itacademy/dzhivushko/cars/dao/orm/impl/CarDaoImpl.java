package by.itacademy.dzhivushko.cars.dao.orm.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import by.itacademy.dzhivushko.cars.dao.api.ICarDao;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.ICar;
import by.itacademy.dzhivushko.cars.dao.api.filter.CarFilter;
import by.itacademy.dzhivushko.cars.dao.orm.impl.entity.Car;
import by.itacademy.dzhivushko.cars.dao.orm.impl.entity.Car_;
import by.itacademy.dzhivushko.cars.dao.orm.impl.entity.Model_;

@Repository
public class CarDaoImpl extends AbstractDaoImpl<ICar, Integer> implements ICarDao {

	protected CarDaoImpl() {
		super(Car.class);
	}

	@Override
	public ICar createEntity() {
		Car car = new Car();
		return car;
	}

	@Override
	public ICar getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<ICar> cq = cb.createQuery(ICar.class);
		final Root<Car> from = cq.from(Car.class);

		cq.select(from);

		from.fetch(Car_.model, JoinType.LEFT);

		// .. where id=...
		cq.where(cb.equal(from.get(Car_.id), id));

		final TypedQuery<ICar> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public long getCount(final CarFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Car> from = cq.from(Car.class);
		cq.select(cb.count(from));
		applyFilter(filter, cb, cq, from);
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<ICar> find(final CarFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ICar> cq = cb.createQuery(ICar.class);
		final Root<Car> from = cq.from(Car.class);
		cq.select(from);

		from.fetch(Car_.model, JoinType.LEFT);

		applyFilter(filter, cb, cq, from);

		// set sort params
		if (filter.getSortColumn() != null) {
			final Path<?> expression = getSortPath(from, filter.getSortColumn());
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<ICar> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private void applyFilter(final CarFilter filter, final CriteriaBuilder cb, final CriteriaQuery<?> cq,
			final Root<Car> from) {
		final List<Predicate> ands = new ArrayList<>();

		final String vin = filter.getVin();
		if (!StringUtils.isEmpty(vin)) {
			ands.add(cb.equal(from.get(Car_.vin), vin));
		}
		final Boolean sold = filter.getSold();
		if (Boolean.FALSE.equals(sold)) {
			ands.add(cb.equal(from.get(Car_.sold), false));
		}

		if (!ands.isEmpty()) {
			cq.where(cb.and(ands.toArray(new Predicate[0])));
		}
	}

	@Override
	public ICar getNewestCar() {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ICar> cq = cb.createQuery(ICar.class);
		final Root<Car> from = cq.from(Car.class);
		cq.select(from);
		cq.orderBy(new OrderImpl(from.get(Car_.created), false));
		final TypedQuery<ICar> q = em.createQuery(cq);
		q.setMaxResults(1);
		List<ICar> resultList = q.getResultList();
		return resultList.isEmpty() ? null : resultList.get(0);
	}

	private Path<?> getSortPath(final Root<Car> from, final String sortColumn) {
		switch (sortColumn) {
		case "created":
			return from.get(Car_.created);
		case "updated":
			return from.get(Car_.updated);
		case "id":
			return from.get(Car_.id);
		case "sold":
			return from.get(Car_.sold);
		case "sold_date":
			return from.get(Car_.soldDate);
		case "vin":
			return from.get(Car_.vin);
		case "model":
			return from.get(Car_.model).get(Model_.name);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

}
