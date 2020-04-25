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
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Company;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Company_;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Employee_;
import by.itacademy.tatabakach.transportcompany.daoapi.ICompanyDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.ICompany;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.CompanyFilter;

@Repository
public class CompanyDaoImpl extends AbstractDaoImpl<ICompany, Integer> implements ICompanyDao {

	protected CompanyDaoImpl() {
		super(Company.class);
	}

	@Override
	public ICompany createEntity() {
		return new Company();
	}

	@Override
	public ICompany getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<ICompany> cq = cb.createQuery(ICompany.class);
		final Root<Company> from = cq.from(Company.class);

		cq.select(from);

		from.fetch(Company_.legalAddress, JoinType.LEFT);
		from.fetch(Company_.postAddress, JoinType.LEFT);
		from.fetch(Company_.creator, JoinType.LEFT);
		
		from.fetch(Company_.employees, JoinType.LEFT);
		cq.distinct(true); // to avoid duplicate rows in result

		// .. where id=...
		cq.where(cb.equal(from.get(Company_.id), id));

		final TypedQuery<ICompany> q = em.createQuery(cq);

		return q.getSingleResult();
	}

	@Override
	public List<ICompany> find(final CompanyFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<ICompany> cq = cb.createQuery(ICompany.class);
		final Root<Company> from = cq.from(Company.class);
		cq.select(from);
		
		if(filter.getFetchLegalAddress()) {
			from.fetch(Company_.legalAddress, JoinType.LEFT).fetch(Address_.locality, JoinType.LEFT);
		}
		if(filter.getFetchPostAddress()) {
			from.fetch(Company_.postAddress, JoinType.LEFT).fetch(Address_.locality, JoinType.LEFT);
		}
		if(filter.getFetchCreator()) {
			from.fetch(Company_.creator, JoinType.LEFT);
		}

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<ICompany> q = em.createQuery(cq);

		setPaging(filter, q);
		final List<ICompany> resultList = q.getResultList();
		return resultList;
	}

	@Override
	public long getCount(final CompanyFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Company> from = cq.from(Company.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	private Path<?> getSortPath(final Root<Company> from, final String sortColumn) {
		switch (sortColumn) {
		case "id":
			return from.get(Company_.id);
		case "companyType":
			return from.get(Company_.companyType);
		case "name":
			return from.get(Company_.name);
		case "payerRegistrationNumber":
			return from.get(Company_.payerRegistrationNumber);
		case "legalAddress":
			return from.get(Company_.legalAddress).get(Address_.id);
		case "postAddress":
			return from.get(Company_.postAddress).get(Address_.id);
		case "bankData":
			return from.get(Company_.bankData);
		case "eMail":
			return from.get(Company_.mail);
		case "phone":
			return from.get(Company_.phone);
		case "creator":
			return from.get(Company_.creator).get(Employee_.id);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

}
