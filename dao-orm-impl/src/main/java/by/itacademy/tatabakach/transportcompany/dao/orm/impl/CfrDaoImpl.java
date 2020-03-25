package by.itacademy.tatabakach.transportcompany.dao.orm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Cfr;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Cfr_;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Company_;
import by.itacademy.tatabakach.transportcompany.daoapi.ICfrDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICfr;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CfrFilter;

@Repository
public class CfrDaoImpl extends AbstractDaoImpl<ICfr, Integer> implements ICfrDao {


	protected CfrDaoImpl() {
		super(Cfr.class);
	}

	@Override
	public ICfr createEntity() {
		return new Cfr();
	}

	@Override
	public ICfr getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<ICfr> cq = cb.createQuery(ICfr.class);
		final Root<Cfr> from = cq.from(Cfr.class);

		cq.select(from);

		from.fetch(Cfr_.company, JoinType.LEFT); // подтягивание объекта company

		// .. where id=...
		cq.where(cb.equal(from.get(Cfr_.id), id));

		final TypedQuery<ICfr> q = em.createQuery(cq);

		return q.getSingleResult();
	}

	@Override
	public List<ICfr> find(final CfrFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<ICfr> cq = cb.createQuery(ICfr.class);
		final Root<Cfr> from = cq.from(Cfr.class);
		cq.select(from);

		if (filter.getFetchCompany()) {
			// select m, b from model m left join brand b ...
			from.fetch(Cfr_.company, JoinType.LEFT);
		}

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<ICfr> q = em.createQuery(cq);

		setPaging(filter, q);
		final List<ICfr> resultList = q.getResultList();
		return resultList;
	}

	@Override
	public long getCount(final CfrFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Cfr> from = cq.from(Cfr.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	private Path<?> getSortPath(final Root<Cfr> from, final String sortColumn) {
		switch (sortColumn) {
		case "id":
			return from.get(Cfr_.id);
		case "company":
			return from.get(Cfr_.company).get(Company_.id);
		case "year":
			return from.get(Cfr_.year);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

}
