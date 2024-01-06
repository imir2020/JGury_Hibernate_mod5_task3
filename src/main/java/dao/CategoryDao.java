package dao;

import entity.Category;
import utils.HibernateUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Создать 3 дополнительных метода вызова данных из базы, + использовать entityGraph API для решения проблемы N + 1
 */

public class CategoryDao implements Dao<Long, Category> {
    private static final CategoryDao INSTANCE = new CategoryDao();

    private CategoryDao() {
    }

    public static CategoryDao getInstance() {
        return INSTANCE;
    }


    @Override
    public List<Category> findAll() {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            List<Category> list = session.createQuery("from Category", Category.class).list();
            session.getTransaction().commit();
            return list;
        }
    }

    @Override
    public Optional<Category> findById(Long id) {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Category category = session.get(Category.class, id);
            session.getTransaction().commit();
            return Optional.ofNullable(category);
        }
    }

    @Override
    public Category save(Category category) {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.save(category);
            Category result = session.get(Category.class, (Serializable) category);
            session.getTransaction().commit();
            return result;
        }
    }

    @Override
    public boolean update(Category category) {
        boolean isCommit = false;
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.update(category);
            session.getTransaction().commit();
            isCommit = true;
        }
        return isCommit;
    }

    @Override
    public boolean delete(Long id) {
        boolean isDelete = false;
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Category category = session.get(Category.class, id);
            session.delete(category);
            session.getTransaction().commit();
            isDelete = true;
        }
        return isDelete;
    }

}