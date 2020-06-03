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

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Car_;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Company_;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Driver_;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Employee_;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Order;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Order_;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Tax_;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.TransactionCost_;
import by.itacademy.tatabakach.transportcompany.daoapi.IOrderDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IOrder;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.OrderFilter;

@Repository
public class OrderDaoImpl extends AbstractDaoImpl<IOrder, Integer> implements IOrderDao {


	protected OrderDaoImpl() {
		super(Order.class);
	}

	@Override
	public IOrder createEntity() {
		return new Order();
	}

	@Override
	public IOrder getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IOrder> cq = cb.createQuery(IOrder.class);
		final Root<Order> from = cq.from(Order.class);

		cq.select(from);

		from.fetch(Order_.ourCompany, JoinType.LEFT);
		from.fetch(Order_.customer, JoinType.LEFT);
		from.fetch(Order_.carrier, JoinType.LEFT);
		from.fetch(Order_.car, JoinType.LEFT);
		from.fetch(Order_.driver, JoinType.LEFT);
		from.fetch(Order_.customerCost, JoinType.LEFT);
		from.fetch(Order_.carrierCost, JoinType.LEFT);
		from.fetch(Order_.tax, JoinType.LEFT);
		from.fetch(Order_.creator, JoinType.LEFT);
		
		from.fetch(Order_.employees, JoinType.LEFT);
		cq.distinct(true); // to avoid duplicate rows in result

		// .. where id=...
		cq.where(cb.equal(from.get(Order_.id), id));

		final TypedQuery<IOrder> q = em.createQuery(cq);

		return q.getSingleResult();
	}

	@Override
	public List<IOrder> find(final OrderFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IOrder> cq = cb.createQuery(IOrder.class);
		final Root<Order> from = cq.from(Order.class);
		cq.select(from);
		
		if (filter.getFetchOurCompany()) {
			from.fetch(Order_.ourCompany, JoinType.LEFT);
		}
		if (filter.getFetchCustomer()) {
			from.fetch(Order_.customer, JoinType.LEFT);
		}
		if (filter.getFetchCarrier()) {
			from.fetch(Order_.carrier, JoinType.LEFT);
		}
		if (filter.getFetchCar()) {
			from.fetch(Order_.car, JoinType.LEFT);
		}
		if (filter.getFetchDriver()) {
			from.fetch(Order_.driver, JoinType.LEFT);
		}
		if (filter.getFetchCustomerCost()) {
			from.fetch(Order_.customerCost, JoinType.LEFT);
		}
		if (filter.getFetchCarrierCost()) {
			from.fetch(Order_.carrierCost, JoinType.LEFT);
		}
		if (filter.getFetchTax()) {
			from.fetch(Order_.tax, JoinType.LEFT);
		}
		if (filter.getFetchCreator()) {
			from.fetch(Order_.creator, JoinType.LEFT);
		}

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IOrder> q = em.createQuery(cq);

		setPaging(filter, q);
		final List<IOrder> resultList = q.getResultList();
		return resultList;
	}

	@Override
	public long getCount(final OrderFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Order> from = cq.from(Order.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	private Path<?> getSortPath(final Root<Order> from, final String sortColumn) {
		switch (sortColumn) {
		case "id":
			return from.get(Order_.id);
		case "number":
			return from.get(Order_.number);
		case "date":
			return from.get(Order_.date);
		case "ourCompany":
			return from.get(Order_.ourCompany).get(Company_.name);
		case "customer":
			return from.get(Order_.customer).get(Company_.name);
		case "carrier":
			return from.get(Order_.carrier).get(Company_.name);
		case "car":
			return from.get(Order_.car).get(Car_.id);
		case "driver":
			return from.get(Order_.driver).get(Driver_.id);
		case "loadingMethod":
			return from.get(Order_.loadingMethod);
		case "cargoType":
			return from.get(Order_.cargoType);
		case "cargoWeightVolume":
			return from.get(Order_.cargoWeightVolume);
		case "customerCost":
			return from.get(Order_.customerCost).get(TransactionCost_.amount);
		case "paidCustomer":
			return from.get(Order_.paidCustomer);
		case "carrierCost":
			return from.get(Order_.carrierCost).get(TransactionCost_.amount);
		case "paidCarrier":
			return from.get(Order_.paidCarrier);
		case "tax":
			return from.get(Order_.tax).get(Tax_.id);
		case "additionalConditions":
			return from.get(Order_.additionalConditions);
		case "creator":
			return from.get(Order_.creator).get(Employee_.id);
		case "note":
			return from.get(Order_.note);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}
}
