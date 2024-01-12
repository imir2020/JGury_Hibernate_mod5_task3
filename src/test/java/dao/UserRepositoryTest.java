package dao;

import entity.Status;
import entity.User;
import org.hibernate.SessionFactory;
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

import static junit.framework.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public class UserRepositoryTest {
    Logger log = LoggerFactory.getLogger("UserDaoTest");
    private static final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
    private UserRepository userRepository = new UserRepository();

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
        List<User> list = userRepository.findAll();
        assertTrue(list.size() > 0);
        log.info("List of users find in method findAll(): {}", list);

    }

    @Test
    public void findById() {
        Long userId = 2L;
        User user = userRepository.findById(userId).get();
        log.info("Object  was find in method findById(): {}", user);

    }

    @Test
    public void save() {
        User user = User.builder()
                .name("Vadim")
                .birthday(LocalDate.parse("1984-08-12"))
                .password("344")
                .status(Status.ADMIN)
                .build();
        userRepository.save(user);
        log.info("Object was saved in method save(): {}", user);
    }

    @Test
    public void update() {
        Long userId = 2L;
        Status updateStatus = Status.ADMIN;
        User user = userRepository.findById(userId).get();
        user.setStatus(updateStatus);
        userRepository.update(user);
        User userAfterUpdate = userRepository.findById(userId).get();
        assertEquals(updateStatus, userAfterUpdate.getStatus());
        log.info("Object was updated in method update(): {}", user);

    }

    @Test
    public void delete() {
        Long userId = 2L;
        userRepository.delete(userId);
    }

    @Test
    public void findByPassword() {
        String password = "111";
        String query = "select * from users  where password = :password";
        User user = userRepository.findByPassword(password).get();
        assertNotNull(user);
        log.info("Object was find by your password in method findByPassword(): {}", user);
    }

    /**
     * Вывести всех пользователей с заданным статусом
     */
    @Test
    public void findUsersWithСhooseStatus() {
        List<User> users = userRepository.findUsersWithChooseStatus(Status.ADMIN);
        assertThat(users).hasSize(4);
        users.forEach(System.out::println);
    }

    /**
     * Вывести всех пользователей отсортированных по фамилии
     */
    @Test
    public void findAllUsersSortedByName() {
        List<User> users = userRepository.findAllUsersSortedByName();
        assertThat(users).hasSize(5);
        users.forEach(System.out::println);
    }

    /**
     * Вывести всех пользователей с датой рождения меньше указанной
     */
    @Test
    public void findAllUsersByBirthday() {
        List<User> users = userRepository.findAllUsersByBirthday(LocalDate.parse("1995-01-11"));
        assertThat(users).hasSize(4);
        users.forEach(System.out::println);
    }
}
