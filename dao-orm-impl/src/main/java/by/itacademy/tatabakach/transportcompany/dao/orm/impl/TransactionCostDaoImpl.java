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

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.TransactionCost;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.TransactionCost_;
import by.itacademy.tatabakach.transportcompany.daoapi.ITransactionCostDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ITransactionCost;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.TransactionCostFilter;

@Repository
public class TransactionCostDaoImpl extends AbstractDaoImpl<ITransactionCost, Integer> implements ITransactionCostDao {

	protected TransactionCostDaoImpl() {
		super(TransactionCost.class);
	}

	@Override
	public ITransactionCost createEntity() {
		return new TransactionCost();
	}

	@Override
	public List<ITransactionCost> find(final TransactionCostFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ITransactionCost> cq = cb.createQuery(ITransactionCost.class);
		final Root<TransactionCost> from = cq.from(TransactionCost.class);
		cq.select(from);

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<ITransactionCost> q = em.createQuery(cq);

		setPaging(filter, q);
		return q.getResultList();
	}

	@Override
	public long getCount(final TransactionCostFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<TransactionCost> from = cq.from(TransactionCost.class);
		cq.select(cb.count(from));
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	private Path<?> getSortPath(final Root<TransactionCost> from, final String sortColumn) {
	        switch (sortColumn) {
	        case "id":
	            return from.get(TransactionCost_.id);
	        case "date":
	            return from.get(TransactionCost_.date);
	        case "currency":
	            return from.get(TransactionCost_.currency);
	        case "amount":
	            return from.get(TransactionCost_.amount);
	        case "rate":
	            return from.get(TransactionCost_.rate);
	        case "intermediateCurrency":
	            return from.get(TransactionCost_.intermediateCurrency);
	        case "intermediateCurrencyRate":
	            return from.get(TransactionCost_.intermediateCurrencyRate);
	        case "paymentPeriod":
	            return from.get(TransactionCost_.paymentPeriod);
	        case "paymentTermsType":
	            return from.get(TransactionCost_.paymentTermsType);
	        case "note":
	            return from.get(TransactionCost_.note);
	        default:
	            throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
	        }
	}

}
