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

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Country_;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Region;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Region_;
import by.itacademy.tatabakach.transportcompany.daoapi.IRegionDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRegion;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.RegionFilter;

@Repository
public class RegionDaoImpl extends AbstractDaoImpl<IRegion, Integer> implements IRegionDao {

	protected RegionDaoImpl() {
		super(Region.class);
	}

	@Override
	public IRegion createEntity() {
		return new Region();
	}
	
	
	@Override
	public IRegion getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IRegion> cq = cb.createQuery(IRegion.class);
		final Root<Region> from = cq.from(Region.class);

		cq.select(from);

		from.fetch(Region_.country, JoinType.LEFT); // подтягивание объекта country

		// .. where id=...
		cq.where(cb.equal(from.get(Region_.id), id));

		final TypedQuery<IRegion> q = em.createQuery(cq);

		//return getSingleResult(q);
		return q.getSingleResult();
	}

	@Override
	public List<IRegion> find(final RegionFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IRegion> cq = cb.createQuery(IRegion.class);
		final Root<Region> from = cq.from(Region.class);
		cq.select(from);

		if (filter.getFetchCountry()) {
			// select m, b from model m left join brand b ...
			from.fetch(Region_.country, JoinType.LEFT);
		}

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IRegion> q = em.createQuery(cq);

		setPaging(filter, q);
		final List<IRegion> resultList = q.getResultList();
		return resultList;
	}

	@Override
	public long getCount(final RegionFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Region> from = cq.from(Region.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	private Path<?> getSortPath(final Root<Region> from, final String sortColumn) {
		switch (sortColumn) {
		case "id":
			return from.get(Region_.id);
		case "name":
			return from.get(Region_.name);
		case "country":
			return from.get(Region_.country).get(Country_.id);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

}
