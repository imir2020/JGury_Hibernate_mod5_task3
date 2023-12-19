package dao;

import entity.Status;
import entity.User;
import jakarta.persistence.TypedQuery;
import lombok.Cleanup;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.HibernateUtil;

import java.time.LocalDate;
import java.util.List;

public class UserDaoTest {
    Logger log = LoggerFactory.getLogger("UserDaoTest");

    @Test
    public void findAll() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> list = session.createQuery("from User", User.class).list();
        session.getTransaction().commit();
        log.info("List of users find in method findAll(): {}", list);

    }

    @Test
    public void findById() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class, 1L);
        session.getTransaction().commit();
        log.info("Object  was find in method findById(): {}", user);

    }

    @Test
    public void save() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        User user = User.builder()
                .name("Vadim")
                .birthday(LocalDate.parse("1984-08-12"))
                .password("344")
                .status(Status.ADMIN)
                .build();
        session.save(user);
        session.getTransaction().commit();
        log.info("Object was saved in method save(): {}", user);
    }

    @Test
    public void update() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class, 2);
        user.setStatus(Status.ADMIN);
        session.update(user);
        session.getTransaction().commit();
        log.info("Object was updated in method update(): {}", user);

    }

    @Test
    public void delete() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class, 1);
        session.delete(user);
        session.getTransaction().commit();
    }

    @Test
    public void findByPassword() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        String password = "111";
        String query = "select * from users  where password = :password";
       Query result = session.createNativeQuery(query, User.class);
       result.setParameter("password",password);
        List<User> users = result.getResultList();
        session.getTransaction().commit();
        log.info("Object was find by your password in method findByPassword(): {}", users);
    }
}
