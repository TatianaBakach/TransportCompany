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

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Company_;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Correspondence;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Correspondence_;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Order_;
import by.itacademy.tatabakach.transportcompany.daoapi.ICorrespondenceDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICorrespondence;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CorrespondenceFilter;

@Repository
public class CorrespondenceDaoImpl extends AbstractDaoImpl<ICorrespondence, Integer> implements ICorrespondenceDao {

	protected CorrespondenceDaoImpl() {
		super(Correspondence.class);
	}

	@Override
	public ICorrespondence createEntity() {
		return new Correspondence();
	}

	@Override
	public ICorrespondence getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<ICorrespondence> cq = cb.createQuery(ICorrespondence.class);
		final Root<Correspondence> from = cq.from(Correspondence.class);

		cq.select(from);

		from.fetch(Correspondence_.order, JoinType.LEFT);
		from.fetch(Correspondence_.company, JoinType.LEFT);

		// .. where id=...
		cq.where(cb.equal(from.get(Correspondence_.id), id));

		final TypedQuery<ICorrespondence> q = em.createQuery(cq);

		return q.getSingleResult();
	}
	
	
	@Override
	    public List<ICorrespondence> find(final CorrespondenceFilter filter) {
	        final EntityManager em = getEntityManager();
	        final CriteriaBuilder cb = em.getCriteriaBuilder();
	       
	        final CriteriaQuery<ICorrespondence> cq = cb.createQuery(ICorrespondence.class);
	        final Root<Correspondence> from = cq.from(Correspondence.class);
	        cq.select(from);

	        if (filter.getFetchOrder()) {
				from.fetch(Correspondence_.order, JoinType.LEFT);
			}
	        if(filter.getFetchCompany()) {
	        	from.fetch(Correspondence_.company, JoinType.LEFT);
	        }
	        
	        final String sortColumn = filter.getSortColumn();
	        if (sortColumn != null) {
	            final Path<?> expression = getSortPath(from, sortColumn);
	            cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
	        }

	        final TypedQuery<ICorrespondence> q = em.createQuery(cq);

	        setPaging(filter, q);
	        final List<ICorrespondence> resultList = q.getResultList();
	        return resultList;
	    }
	
	    @Override
	    public long getCount(final CorrespondenceFilter filter) {
	        final EntityManager em = getEntityManager();
	        final CriteriaBuilder cb = em.getCriteriaBuilder();
	        
	        final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
	        final Root<Correspondence> from = cq.from(Correspondence.class);
	        
	        cq.select(cb.count(from));
	        
	        final TypedQuery<Long> q = em.createQuery(cq);
	        return q.getSingleResult();
	    }
	    
	    private Path<?> getSortPath(final Root<Correspondence> from, final String sortColumn) {
	        switch (sortColumn) {
	        case "id":
	            return from.get(Correspondence_.id);
	        case "correspondenceType":
	            return from.get(Correspondence_.correspondenceType);
	        case "order":
	            return from.get(Correspondence_.order).get(Order_.id);
	        case "company":
	            return from.get(Correspondence_.company).get(Company_.id);
	        case "date":
	            return from.get(Correspondence_.date);
	        case "content":
	            return from.get(Correspondence_.content);
	        case "note":
	            return from.get(Correspondence_.note);
	        default:
	            throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
	        }
	    }

}
