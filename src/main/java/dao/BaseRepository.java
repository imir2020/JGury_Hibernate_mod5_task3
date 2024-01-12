package dao;

import entity.BaseEntity;
import entity.Category;
import lombok.Cleanup;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BaseRepository<K extends Serializable, E extends BaseEntity<K>> implements Repository<K, E> {

    private final Class<E> clazz;
    private final SessionFactory sessionFactory;


    @Override
    public E save(E entity) {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public void delete(K id) {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(session.find(clazz, id));
        session.flush();
        session.getTransaction().commit();
    }

    @Override
    public void update(E entity) {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println(entity);
        session.merge(entity);
        session.flush();
        session.getTransaction().commit();

    }

    @Override
    public Optional<E> findById(K id) {
        @Cleanup var session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        return Optional.ofNullable(session.find(clazz, id));

    }

    @Override
    public List<E> findAll() {
        @Cleanup var session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        var criteria = session.getCriteriaBuilder().createQuery(clazz);
        criteria.from(clazz);
        return session.createQuery(criteria).getResultList();
    }
}
