package by.itacademy.dzhivushko.cars.dao.orm.impl;

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

import by.itacademy.dzhivushko.cars.dao.api.IEngineDao;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IEngine;
import by.itacademy.dzhivushko.cars.dao.api.filter.EngineFilter;
import by.itacademy.dzhivushko.cars.dao.orm.impl.entity.Engine;
import by.itacademy.dzhivushko.cars.dao.orm.impl.entity.Engine_;

@Repository
public class EngineDaoImpl extends AbstractDaoImpl<IEngine, Integer> implements IEngineDao {

    protected EngineDaoImpl() {
        super(Engine.class);
    }

    @Override
    public IEngine createEntity() {
        return new Engine();
    }

    @Override
    public List<IEngine> find(final EngineFilter filter) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<IEngine> cq = cb.createQuery(IEngine.class);
        final Root<Engine> from = cq.from(Engine.class);
        cq.select(from);

        final String sortColumn = filter.getSortColumn();
        if (sortColumn != null) {
            final Path<?> expression = getSortPath(from, sortColumn);
            cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
        }

        final TypedQuery<IEngine> q = em.createQuery(cq);

        setPaging(filter, q);
        return q.getResultList();
    }

    @Override
    public long getCount(final EngineFilter filter) {
        final EntityManager em = getEntityManager();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        final Root<Engine> from = cq.from(Engine.class);
        cq.select(cb.count(from));
        final TypedQuery<Long> q = em.createQuery(cq);
        return q.getSingleResult();
    }

    private Path<?> getSortPath(final Root<Engine> from, final String sortColumn) {
        switch (sortColumn) {
        case "title":
            return from.get(Engine_.title);
        case "created":
            return from.get(Engine_.created);
        case "updated":
            return from.get(Engine_.updated);
        case "id":
            return from.get(Engine_.id);
        case "volume":
            return from.get(Engine_.volume);
        case "type":
            return from.get(Engine_.type);
        default:
            throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
        }
    }

    @Override
    public Set<IEngine> getByModel(final Integer id) {
        throw new UnsupportedOperationException();
    }

}
