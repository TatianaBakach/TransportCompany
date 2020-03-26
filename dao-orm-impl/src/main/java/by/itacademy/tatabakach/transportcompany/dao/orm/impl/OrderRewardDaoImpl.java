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

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Employee_;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.OrderReward;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.OrderRewardPercent_;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.OrderReward_;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Order_;
import by.itacademy.tatabakach.transportcompany.daoapi.IOrderRewardDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrderReward;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.OrderRewardFilter;

@Repository
public class OrderRewardDaoImpl extends AbstractDaoImpl<IOrderReward, Integer> implements IOrderRewardDao {

	protected OrderRewardDaoImpl() {
		super(OrderReward.class);
	}

	@Override
	public IOrderReward createEntity() {
		return new OrderReward();
	}

	@Override
	public IOrderReward getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IOrderReward> cq = cb.createQuery(IOrderReward.class);
		final Root<OrderReward> from = cq.from(OrderReward.class);

		cq.select(from);

		from.fetch(OrderReward_.order, JoinType.LEFT);
		from.fetch(OrderReward_.employee, JoinType.LEFT);
		from.fetch(OrderReward_.orderRewardPercent, JoinType.LEFT);

		// .. where id=...
		cq.where(cb.equal(from.get(OrderReward_.id), id));

		final TypedQuery<IOrderReward> q = em.createQuery(cq);

		return q.getSingleResult();
	}

	@Override
	public List<IOrderReward> find(final OrderRewardFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IOrderReward> cq = cb.createQuery(IOrderReward.class);
		final Root<OrderReward> from = cq.from(OrderReward.class);
		cq.select(from);
		
		if (filter.getFetchOrder()) {
			from.fetch(OrderReward_.order, JoinType.LEFT);
		}
		if (filter.getFetchEmployee()) {
			from.fetch(OrderReward_.employee, JoinType.LEFT);
		}
		if (filter.getFetchOrderRewardRercent()) {
			from.fetch(OrderReward_.orderRewardPercent, JoinType.LEFT);
		}

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IOrderReward> q = em.createQuery(cq);

		setPaging(filter, q);
		final List<IOrderReward> resultList = q.getResultList();
		return resultList;
	}

	@Override
	public long getCount(final OrderRewardFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<OrderReward> from = cq.from(OrderReward.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	private Path<?> getSortPath(final Root<OrderReward> from, final String sortColumn) {
		switch (sortColumn) {
		case "id":
			return from.get(OrderReward_.id);
		case "order":
			return from.get(OrderReward_.order).get(Order_.id);
		case "employee":
			return from.get(OrderReward_.employee).get(Employee_.id);
		case "rewardType":
			return from.get(OrderReward_.rewardType);
		case "orderRewardPercent":
			return from.get(OrderReward_.orderRewardPercent).get(OrderRewardPercent_.id);
		case "additionalExpenses":
			return from.get(OrderReward_.additionalExpenses);
		case "amount":
			return from.get(OrderReward_.amount);
		case "paymentDate":
			return from.get(OrderReward_.paymentDate);
		case "rewardIssued":
			return from.get(OrderReward_.rewardIssued);
		case "note":
			return from.get(OrderReward_.note);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

}
