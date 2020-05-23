package by.itacademy.tatabakach.transportcompany.dao.orm.impl;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Employee;
import by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity.Employee_;
import by.itacademy.tatabakach.transportcompany.daoapi.IEmployeeDao;
import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IEmployee;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.EmployeeFilter;

@Repository
public class EmployeeDaoImpl extends AbstractDaoImpl<IEmployee, Integer> implements IEmployeeDao {

	protected EmployeeDaoImpl() {
		super(Employee.class);
	}

	@Override
	public IEmployee createEntity() {
		return new Employee();
	}

	@Override
	public List<IEmployee> find(final EmployeeFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IEmployee> cq = cb.createQuery(IEmployee.class);
		final Root<Employee> from = cq.from(Employee.class);
		cq.select(from);

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IEmployee> q = em.createQuery(cq);

		setPaging(filter, q);
		final List<IEmployee> resultList = q.getResultList();
		return resultList;
	}

	@Override
	public long getCount(final EmployeeFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Employee> from = cq.from(Employee.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	private Path<?> getSortPath(final Root<Employee> from, final String sortColumn) {
		switch (sortColumn) {
		case "id":
			return from.get(Employee_.id);
		case "firstName":
			return from.get(Employee_.firstName);
		case "middleName":
			return from.get(Employee_.middleName);
		case "lastName":
			return from.get(Employee_.lastName);
		case "department":
			return from.get(Employee_.department);
		case "position":
			return from.get(Employee_.position);
		case "mail":
			return from.get(Employee_.mail);
		case "phone":
			return from.get(Employee_.phone);
		case "role":
			return from.get(Employee_.role);
		case "password":
			return from.get(Employee_.password);
		case "salary":
			return from.get(Employee_.salary);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public Set<IEmployee> getByOrder(Integer id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<IEmployee> getByCompany(Integer id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public IEmployee getByUserName(String username) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IEmployee> cq = cb.createQuery(IEmployee.class);
		final Root<Employee> from = cq.from(Employee.class);

		cq.select(from);

		cq.where(cb.equal(from.get(Employee_.mail), username));

		final TypedQuery<IEmployee> q = em.createQuery(cq);
		return getSingleResult(q);
	}

}
