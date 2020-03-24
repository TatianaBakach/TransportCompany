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

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Locality;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Locality_;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Region_;
import by.itacademy.tatabakach.transportcompany.daoapi.ILocalityDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ILocality;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.LocalityFilter;

@Repository
public class LocalityDaoImpl extends AbstractDaoImpl<ILocality, Integer> implements ILocalityDao {

	protected LocalityDaoImpl() {
		super(Locality.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ILocality createEntity() {
		return new Locality();
	}

	@Override
	public ILocality getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<ILocality> cq = cb.createQuery(ILocality.class);
		final Root<Locality> from = cq.from(Locality.class);

		cq.select(from);

		from.fetch(Locality_.region, JoinType.LEFT).fetch(Region_.country, JoinType.LEFT);

		// .. where id=...
		cq.where(cb.equal(from.get(Locality_.id), id));

		final TypedQuery<ILocality> q = em.createQuery(cq);

		return getSingleResult(q);
	}
	
	
	@Override
	    public List<ILocality> find(final LocalityFilter filter) {
	        final EntityManager em = getEntityManager();
	        final CriteriaBuilder cb = em.getCriteriaBuilder();
	       
	        final CriteriaQuery<ILocality> cq = cb.createQuery(ILocality.class);
	        final Root<Locality> from = cq.from(Locality.class);
	        cq.select(from);

	        if (filter.getFetchRegion()) {
				// select m, b from model m left join brand b ...
				from.fetch(Locality_.region, JoinType.LEFT);
			}
	        
	        final String sortColumn = filter.getSortColumn();
	        if (sortColumn != null) {
	            final Path<?> expression = getSortPath(from, sortColumn);
	            cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
	        }

	        final TypedQuery<ILocality> q = em.createQuery(cq);

	        setPaging(filter, q);
	        final List<ILocality> resultList = q.getResultList();
	        return resultList;
	    }
	
	    @Override
	    public long getCount(final LocalityFilter filter) {
	        final EntityManager em = getEntityManager();
	        final CriteriaBuilder cb = em.getCriteriaBuilder();
	        
	        final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
	        final Root<Locality> from = cq.from(Locality.class);
	        
	        cq.select(cb.count(from));
	        
	        final TypedQuery<Long> q = em.createQuery(cq);
	        return q.getSingleResult();
	    }
	    
	    private Path<?> getSortPath(final Root<Locality> from, final String sortColumn) {
	        switch (sortColumn) {
	        case "id":
	            return from.get(Locality_.id);
	        case "name":
	            return from.get(Locality_.name);
	        case "country":
	            return from.get(Locality_.region).get(Region_.id);
	        default:
	            throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
	        }
	    }

}
