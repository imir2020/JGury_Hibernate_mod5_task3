package dao;

import entity.Status;
import entity.User;
import lombok.Cleanup;
import lombok.SneakyThrows;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class UserRepository extends BaseRepository<Long, User> {
    private static SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public UserRepository() {
        super(User.class, sessionFactory);
    }

    /**
     * Найти пользователя по паролю
     */
    @SneakyThrows
    public Optional<User> findByPassword(String password) {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        String query = "select * from users  where password = :password";
        Query result = session.createNativeQuery(query, User.class);
        result.setParameter("password", password);
        List<User> users = result.getResultList();
        session.getTransaction().commit();
        return Optional.ofNullable(users.get(0));
    }

    /**
     * Вывести всех пользователей с заданным статусом
     */
    public List<User> findUsersWithChooseStatus(Status status) {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
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
    public List<User> findAllUsersSortedByName() {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> users = session.createQuery("""
                select u from User u 
                order by u.name
                """).list();
        session.getTransaction().commit();
        return users;
    }

    /**
     * Вывести всех пользователей с датой рождения меньше указанной,
     * и отсортированные по убыванию дат рождения.
     */
    public List<User> findAllUsersByBirthday(LocalDate birthDay) {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> users = session.createQuery("""
                        select u from User u 
                        where u.birthday < :birthday
                        order by u.birthday desc 
                        """).setParameter("birthday", birthDay)
                .list();
        session.getTransaction().commit();
        return users;
    }
}
