package dao;

import entity.Status;
import entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Создать 3 дополнительных метода вызова данных из базы, +  использовать entityGraph API для решения проблемы N + 1
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class UserDao implements Dao<Long, User> {
    private final static UserDao INSTANCE = new UserDao();
    private final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public static UserDao getInstance() {
        return INSTANCE;
    }

    /**
     * Найти пользователя по паролю
     */
    @SneakyThrows
    public Optional<User> findByPassword(String password) {
        try (var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            String query = "select * from users  where password = :password";
            Query result = session.createNativeQuery(query, User.class);
            result.setParameter("password", password);
            List<User> users = result.getResultList();
            session.getTransaction().commit();
            return Optional.ofNullable(users.get(0));
        }
    }

    /**
     * Найти всех
     */
    @Override
    public List<User> findAll() {
        try (var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            List<User> list = session.createQuery("from User", User.class).list();
            session.getTransaction().commit();
            return list;
        }
    }

    /**
     * Найти пользователя по id
     */
    @Override
    public Optional<User> findById(Long id) {
        try (var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            User user = session.get(User.class, id);
            session.getTransaction().commit();
            return Optional.ofNullable(user);
        }
    }

    /**
     * Сохранить нового пользователя
     */
    @Override
    @SneakyThrows
    public User save(User user) {
        try (var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.save(user);
            session.getTransaction().commit();
        }
        return user;
    }


    /**
     * Изменить данные пользователя
     */

    @Override
    public boolean update(User user) {
        boolean isUpdate = false;
        try (var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.update(user);
            session.getTransaction().commit();
            isUpdate = true;
        }
        return isUpdate;
    }


    /**
     * Удалить пользователя
     */
    @Override
    public boolean delete(Long id) {
        boolean isDelete = false;
        try (var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
            isDelete = true;
        }
        return isDelete;
    }

    /**
     * Вывести всех пользователей с заданным статусом
     */
    public List<User> findUsersWithChooseStatus(Session session, Status status) {
        Status status1;
        List<User> users = session.createQuery("""
                        select u from User u 
                        where u.status =:status
                        """)
                .setParameter("status", status)
                .list();
        session.getTransaction().commit();
        return users;
    }

    /**
     * Вывести всех пользователей отсортированных по фамилии
     */
    public List<User> findAllUsersSortedByName(Session session) {
        List<User> users = session.createQuery("""
                select u from User u 
                order by u.name
                """).list();
        return users;
    }

    /**
     * Вывести всех пользователей с датой рождения меньше указанной,
     *  и отсортированные по убыванию дат рождения.
     */
    public List<User> findAllUsersByBirthday(Session session, LocalDate birthDay) {
        List<User> users = session.createQuery("""
                select u from User u 
                where u.birthday < :birthday
                order by u.birthday desc 
                """).setParameter("birthday",birthDay)
                .list();
        session.getTransaction().commit();
        return users;
    }
}
