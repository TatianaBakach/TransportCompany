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

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Address;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Address_;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Locality_;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Region_;
import by.itacademy.tatabakach.transportcompany.daoapi.IAddressDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IAddress;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.AddressFilter;

@Repository
public class AddressDaoImpl extends AbstractDaoImpl<IAddress, Integer> implements IAddressDao {

	protected AddressDaoImpl() {
		super(Address.class);
	}

	@Override
	public IAddress createEntity() {
		return new Address();
	}

	@Override
	public IAddress getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IAddress> cq = cb.createQuery(IAddress.class);
		final Root<Address> from = cq.from(Address.class);

		cq.select(from);

		from.fetch(Address_.locality, JoinType.LEFT).fetch(Locality_.region, JoinType.LEFT).fetch(Region_.country, JoinType.LEFT);;

		// .. where id=...
		cq.where(cb.equal(from.get(Address_.id), id));

		final TypedQuery<IAddress> q = em.createQuery(cq);

		return q.getSingleResult();
	}
	
	
	@Override
	    public List<IAddress> find(final AddressFilter filter) {
	        final EntityManager em = getEntityManager();
	        final CriteriaBuilder cb = em.getCriteriaBuilder();
	       
	        final CriteriaQuery<IAddress> cq = cb.createQuery(IAddress.class);
	        final Root<Address> from = cq.from(Address.class);
	        cq.select(from);

	        if (filter.getFetchLocality()) {
				// select m, b from model m left join brand b ...
				from.fetch(Address_.locality, JoinType.LEFT);
			}
	        
	        final String sortColumn = filter.getSortColumn();
	        if (sortColumn != null) {
	            final Path<?> expression = getSortPath(from, sortColumn);
	            cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
	        }

	        final TypedQuery<IAddress> q = em.createQuery(cq);

	        setPaging(filter, q);
	        final List<IAddress> resultList = q.getResultList();
	        return resultList;
	    }
	
	    @Override
	    public long getCount(final AddressFilter filter) {
	        final EntityManager em = getEntityManager();
	        final CriteriaBuilder cb = em.getCriteriaBuilder();
	        
	        final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
	        final Root<Address> from = cq.from(Address.class);
	        
	        cq.select(cb.count(from));
	        
	        final TypedQuery<Long> q = em.createQuery(cq);
	        return q.getSingleResult();
	    }
	    
	    private Path<?> getSortPath(final Root<Address> from, final String sortColumn) {
	        switch (sortColumn) {
	        case "id":
	            return from.get(Address_.id);
	        case "postcode":
	            return from.get(Address_.postcode);
	        case "locality":
	            return from.get(Address_.locality).get(Locality_.id);
	        case "exactAddress":
	            return from.get(Address_.exactAddress);
	        case "note":
	            return from.get(Address_.note);
	        default:
	            throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
	        }
	    }

}
