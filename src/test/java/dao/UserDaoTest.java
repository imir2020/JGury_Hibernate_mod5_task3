package dao;

import entity.Status;
import entity.User;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.HibernateUtil;
import utils.TestDataImporter;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public class UserDaoTest {
    Logger log = LoggerFactory.getLogger("UserDaoTest");
    private final UserDao userDao = UserDao.getInstance();
    private static final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    @BeforeAll
    static void initDb() {
        TestDataImporter.importData(sessionFactory);
    }

    @AfterAll

    static void finish() {
        sessionFactory.close();
    }

    @Test
    public void findAll() {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> list = session.createQuery("from User", User.class).list();
        session.getTransaction().commit();
        log.info("List of users find in method findAll(): {}", list);

    }

    @Test
    public void findById() {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class, 1L);
        session.getTransaction().commit();
        log.info("Object  was find in method findById(): {}", user);

    }

    @Test
    public void save() {
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
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class, 1);
        session.delete(user);
        session.getTransaction().commit();
    }

    @Test
    public void findByPassword() {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        String password = "111";
        String query = "select * from users  where password = :password";
        Query result = session.createNativeQuery(query, User.class);
        result.setParameter("password", password);
        List<User> users = result.getResultList();
        session.getTransaction().commit();
        log.info("Object was find by your password in method findByPassword(): {}", users);
    }

    /**
     * Вывести всех пользователей с заданным статусом
     */
    @Test
    public void findUsersWithСhooseStatus() {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> users = userDao.findUsersWithChooseStatus(session, Status.ADMIN);
        assertThat(users).hasSize(4);
        users.forEach(System.out::println);
    }

    /**
     * Вывести всех пользователей отсортированных по фамилии
     */
    @Test
    public void findAllUsersSortedByName() {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> users = userDao.findAllUsersSortedByName(session);
        assertThat(users).hasSize(5);
        users.forEach(System.out::println);
    }

    /**
     * Вывести всех пользователей с датой рождения меньше указанной
     */
    @Test
    public void findAllUsersByBirthday() {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> users = userDao.findAllUsersByBirthday(session, LocalDate.parse("1995-01-11"));
        assertThat(users).hasSize(4);
        users.forEach(System.out::println);

    }
}
