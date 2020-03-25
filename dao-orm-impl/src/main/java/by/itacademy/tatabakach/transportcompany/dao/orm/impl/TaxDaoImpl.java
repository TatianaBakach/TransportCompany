package by.itacademy.tatabakach.transportcompany.dao.orm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Tax;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Tax_;
import by.itacademy.tatabakach.transportcompany.daoapi.ITaxDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITax;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.TaxFilter;

@Repository
public class TaxDaoImpl extends AbstractDaoImpl<ITax, Integer> implements ITaxDao {

	protected TaxDaoImpl() {
		super(Tax.class);
	}

	@Override
	public ITax createEntity() {
		return new Tax();
	}
	
	
	// оба варианта работают
	@Override
    public List<ITax> find(final TaxFilter filter) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
       
        final CriteriaQuery<ITax> cq = cb.createQuery(ITax.class);
        final Root<Tax> from = cq.from(Tax.class);
        cq.select(from);

        final String sortColumn = filter.getSortColumn();
        if (sortColumn != null) {
            final Path<?> expression = getSortPath(from, sortColumn);
            cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
        }

        final TypedQuery<ITax> q = em.createQuery(cq);

        setPaging(filter, q);
        final List<ITax> resultList = q.getResultList();
        return resultList;
    }


    @Override
    public long getCount(final TaxFilter filter) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        
        final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        final Root<Tax> from = cq.from(Tax.class);
        
        cq.select(cb.count(from));
        
        final TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult();
    }
    
    private Path<?> getSortPath(final Root<Tax> from, final String sortColumn) {
        switch (sortColumn) {
        case "id":
            return from.get(Tax_.id);
        case "name":
            return from.get(Tax_.name);
        case "rate":
            return from.get(Tax_.rate);
        default:
            throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
        }
    }

	/*@Override
	public long getCount(final TaxFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define
																	// type of result
		final Root<Tax> from = cq.from(Tax.class); // select from brand
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

	@Override
	public List<ITax> find(final TaxFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ITax> cq = cb.createQuery(ITax.class); // define
																		// type
																		// of
		// result
		final Root<Tax> from = cq.from(Tax.class);// select from brand
		cq.select(from); // select what? select *

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super Tax, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); // build path to
																// sort
			// property
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order
																			// by
			// column_name
			// order
		}

		final TypedQuery<ITax> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private SingularAttribute<? super Tax, ?> toMetamodelFormat(final String sortColumn) {
		switch (sortColumn) {
		case "id":
			return Tax_.id;
		case "name":
			return Tax_.name;
		case "rate":
			return Tax_.rate;
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}*/

}
