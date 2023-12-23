package dao;

import entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class UserDao implements Dao<Long, User> {
    private final static UserDao INSTANCE = new UserDao();

    public static UserDao getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    public Optional<User> findByPassword(String password) {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            String query = "select * from users  where password = :password";
            Query result = session.createNativeQuery(query, User.class);
            result.setParameter("password", password);
            List<User> users = result.getResultList();
            session.getTransaction().commit();
            return Optional.ofNullable(users.get(0));
        }
    }

    @Override
    public List<User> findAll() {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            List<User> list = session.createQuery("from User", User.class).list();
            session.getTransaction().commit();
            return list;
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            User user = session.get(User.class, id);
            session.getTransaction().commit();
            return Optional.ofNullable(user);
        }

    }

    @Override
    @SneakyThrows
    public User save(User user) {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.save(user);
            session.getTransaction().commit();
        }
        return user;
    }

    @Override
    public boolean update(User user) {
        boolean isUpdate = false;
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.update(user);
            session.getTransaction().commit();
            isUpdate = true;
        }
        return isUpdate;
    }

    @Override
    public boolean delete(Long id) {
        boolean isDelete =false;
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
            isDelete = true;
        }
        return isDelete;
    }
}
