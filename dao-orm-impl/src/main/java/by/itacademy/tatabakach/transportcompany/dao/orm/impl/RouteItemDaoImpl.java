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

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Address_;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Order_;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.RouteItem;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.RouteItem_;
import by.itacademy.tatabakach.transportcompany.daoapi.IRouteItemDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IRouteItem;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.RouteItemFilter;

@Repository
public class RouteItemDaoImpl extends AbstractDaoImpl<IRouteItem, Integer> implements IRouteItemDao {


	protected RouteItemDaoImpl() {
		super(RouteItem.class);
	}

	@Override
	public IRouteItem createEntity() {
		return new RouteItem();
	}

	@Override
	public IRouteItem getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IRouteItem> cq = cb.createQuery(IRouteItem.class);
		final Root<RouteItem> from = cq.from(RouteItem.class);

		cq.select(from);

		from.fetch(RouteItem_.order, JoinType.LEFT);
		from.fetch(RouteItem_.address, JoinType.LEFT).fetch(Address_.locality, JoinType.LEFT);
		from.fetch(RouteItem_.custom, JoinType.LEFT).fetch(Address_.locality, JoinType.LEFT);

		// .. where id=...
		cq.where(cb.equal(from.get(RouteItem_.id), id));

		final TypedQuery<IRouteItem> q = em.createQuery(cq);

		return q.getSingleResult();
	}
	
	
	@Override
	    public List<IRouteItem> find(final RouteItemFilter filter) {
	        final EntityManager em = getEntityManager();
	        final CriteriaBuilder cb = em.getCriteriaBuilder();
	       
	        final CriteriaQuery<IRouteItem> cq = cb.createQuery(IRouteItem.class);
	        final Root<RouteItem> from = cq.from(RouteItem.class);
	        cq.select(from);

	        if (filter.getFetchOrder()) {
				from.fetch(RouteItem_.order, JoinType.LEFT);
			}
	        if(filter.getFetchAddress()) {
	        	from.fetch(RouteItem_.address, JoinType.LEFT).fetch(Address_.locality, JoinType.LEFT);
	        }
	        if(filter.getFetchCustom()) {
	        	from.fetch(RouteItem_.custom, JoinType.LEFT).fetch(Address_.locality, JoinType.LEFT);
	        }
	        
	        final String sortColumn = filter.getSortColumn();
	        if (sortColumn != null) {
	            final Path<?> expression = getSortPath(from, sortColumn);
	            cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
	        }

	        final TypedQuery<IRouteItem> q = em.createQuery(cq);

	        setPaging(filter, q);
	        final List<IRouteItem> resultList = q.getResultList();
	        return resultList;
	    }
	
	    @Override
	    public long getCount(final RouteItemFilter filter) {
	        final EntityManager em = getEntityManager();
	        final CriteriaBuilder cb = em.getCriteriaBuilder();
	        
	        final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
	        final Root<RouteItem> from = cq.from(RouteItem.class);
	        
	        cq.select(cb.count(from));
	        
	        final TypedQuery<Long> q = em.createQuery(cq);
	        return q.getSingleResult();
	    }
	    
	    private Path<?> getSortPath(final Root<RouteItem> from, final String sortColumn) {
	        switch (sortColumn) {
	        case "id":
	            return from.get(RouteItem_.id);
	        case "order":
	            return from.get(RouteItem_.order).get(Order_.id);
	        case "address":
	            return from.get(RouteItem_.address).get(Address_.id);
	        case "date":
	            return from.get(RouteItem_.date);
	        case "cargoWeight":
	            return from.get(RouteItem_.cargoWeight);
	        case "cargoVolume":
	            return from.get(RouteItem_.cargoVolume);
	        case "	custom":
	            return from.get(RouteItem_.custom).get(Address_.id);
	        case "contactPerson":
	            return from.get(RouteItem_.contactPerson);
	        case "contactPhone":
	            return from.get(RouteItem_.contactPhone);
	        case "note":
	            return from.get(RouteItem_.note);
	        default:
	            throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
	        }
	    }

}
