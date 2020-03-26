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
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Order_;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Payment;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Payment_;
import by.itacademy.tatabakach.transportcompany.daoapi.IPaymentDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IPayment;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.PaymentFilter;

@Repository
public class PaymentDaoImpl extends AbstractDaoImpl<IPayment, Integer> implements IPaymentDao{
	
	protected PaymentDaoImpl() {
		super(Payment.class);
	}

	@Override
	public IPayment createEntity() {
		return new Payment();
	}

	@Override
	public IPayment getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IPayment> cq = cb.createQuery(IPayment.class);
		final Root<Payment> from = cq.from(Payment.class);

		cq.select(from);

		from.fetch(Payment_.order, JoinType.LEFT);
		from.fetch(Payment_.company, JoinType.LEFT);

		// .. where id=...
		cq.where(cb.equal(from.get(Payment_.id), id));

		final TypedQuery<IPayment> q = em.createQuery(cq);

		return q.getSingleResult();
	}
	
	
	@Override
	    public List<IPayment> find(final PaymentFilter filter) {
	        final EntityManager em = getEntityManager();
	        final CriteriaBuilder cb = em.getCriteriaBuilder();
	       
	        final CriteriaQuery<IPayment> cq = cb.createQuery(IPayment.class);
	        final Root<Payment> from = cq.from(Payment.class);
	        cq.select(from);

	        if (filter.getFetchOrder()) {
				from.fetch(Payment_.order, JoinType.LEFT);
			}
	        if(filter.getFetchCompany()) {
	        	from.fetch(Payment_.company, JoinType.LEFT);
	        }
	        
	        final String sortColumn = filter.getSortColumn();
	        if (sortColumn != null) {
	            final Path<?> expression = getSortPath(from, sortColumn);
	            cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
	        }

	        final TypedQuery<IPayment> q = em.createQuery(cq);

	        setPaging(filter, q);
	        final List<IPayment> resultList = q.getResultList();
	        return resultList;
	    }
	
	    @Override
	    public long getCount(final PaymentFilter filter) {
	        final EntityManager em = getEntityManager();
	        final CriteriaBuilder cb = em.getCriteriaBuilder();
	        
	        final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
	        final Root<Payment> from = cq.from(Payment.class);
	        
	        cq.select(cb.count(from));
	        
	        final TypedQuery<Long> q = em.createQuery(cq);
	        return q.getSingleResult();
	    }
	    
	    private Path<?> getSortPath(final Root<Payment> from, final String sortColumn) {
	        switch (sortColumn) {
	        case "id":
	            return from.get(Payment_.id);
	        case "date":
	            return from.get(Payment_.date);
	        case "order":
	            return from.get(Payment_.order).get(Order_.id);
	        case "company":
	            return from.get(Payment_.company).get(Company_.id);
	        case "currency":
	            return from.get(Payment_.currency);
	        case "rate":
	            return from.get(Payment_.rate);
	        case "amount":
	            return from.get(Payment_.amount);
	        case "note":
	            return from.get(Payment_.note);
	        default:
	            throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
	        }
	    }


}
