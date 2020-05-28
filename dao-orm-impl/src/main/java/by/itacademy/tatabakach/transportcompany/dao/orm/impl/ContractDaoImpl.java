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
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Contract;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Contract_;
import by.itacademy.tatabakach.transportcompany.daoapi.IContractDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IContract;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.ContractFilter;

@Repository
public class ContractDaoImpl extends AbstractDaoImpl<IContract, Integer> implements IContractDao {


	protected ContractDaoImpl() {
		super(Contract.class);
	}

	@Override
	public IContract createEntity() {
		return new Contract();
	}

	@Override
	public IContract getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IContract> cq = cb.createQuery(IContract.class);
		final Root<Contract> from = cq.from(Contract.class);

		cq.select(from);

		from.fetch(Contract_.ourCompany, JoinType.LEFT);
		from.fetch(Contract_.company, JoinType.LEFT);

		// .. where id=...
		cq.where(cb.equal(from.get(Contract_.id), id));

		final TypedQuery<IContract> q = em.createQuery(cq);

		return getSingleResult(q);
	}
	
	@Override
	public List<IContract> find(final ContractFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IContract> cq = cb.createQuery(IContract.class);
		final Root<Contract> from = cq.from(Contract.class);
		cq.select(from);
		
		if (filter.getFetchOurCompany()) {
			// select m, b from model m left join brand b ...
			from.fetch(Contract_.ourCompany, JoinType.LEFT);
		}

		if (filter.getFetchCompany()) {
			// select m, b from model m left join brand b ...
			from.fetch(Contract_.company, JoinType.LEFT);
		}
		
		
		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IContract> q = em.createQuery(cq);

		setPaging(filter, q);
		final List<IContract> resultList = q.getResultList();
		return resultList;
	}

	@Override
	public long getCount(final ContractFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Contract> from = cq.from(Contract.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	private Path<?> getSortPath(final Root<Contract> from, final String sortColumn) {
		switch (sortColumn) {
		case "id":
			return from.get(Contract_.id);
		case "number":
			return from.get(Contract_.number);
		case "ourCompany":
			return from.get(Contract_.ourCompany).get(Company_.id);
		case "company":
			return from.get(Contract_.company).get(Company_.id);
		case "date":
			return from.get(Contract_.date);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

}
