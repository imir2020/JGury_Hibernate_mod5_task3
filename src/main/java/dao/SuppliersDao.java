package dao;

import entity.Suppliers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateUtil;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Создать 3 дополнительных метода вызова данных из базы, +  использовать entityGraph API для решения проблемы N + 1
 */
public class SuppliersDao implements Dao<Long, Suppliers> {
    private static final SuppliersDao INSTANCE = new SuppliersDao();
    private final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    private SuppliersDao() {
    }

    public static SuppliersDao getInstance() {
        return INSTANCE;
    }


    /**
     * Найти всех поставщиков. Возможно стоит добавить в параметры crud методов параметр session? - подумать над этим.
     */
    @Override
    public List<Suppliers> findAll() {
        try (var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            List<Suppliers> list = session.createQuery("select s from Suppliers s", Suppliers.class).list();
            list.forEach(System.out::println);
            session.getTransaction().commit();
            return list;
        }
    }

    @Override
    public Optional<Suppliers> findById(Long id) {
        try (var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Suppliers supplier = session.get(Suppliers.class, id);
            session.getTransaction().commit();
            return Optional.ofNullable(supplier);
        }
    }

    @Override
    public Suppliers save(Suppliers supplier) {
        try (var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.save(supplier);
            session.getTransaction().commit();
            return null;
        }
    }

    @Override
    public boolean update(Suppliers suppliers) {
        boolean isUpdate = false;
        try (var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.update(suppliers);
            session.getTransaction().commit();
            isUpdate = true;
        }
        return isUpdate;
    }

    @Override
    public boolean delete(Long id) {
        boolean isDelete = false;
        try (var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Suppliers supplier = session.get(Suppliers.class, id);
            session.delete(supplier);
            session.getTransaction().commit();
            isDelete = true;
        }
        return isDelete;
    }

    /**
     * Найти и вывести отсортированный список всех телефонных номеров
     */
    public List<String> listPhoneNumbers(Session session) {
            List<String> list = session.createQuery("""
                    select s.phoneNumber from Suppliers s
                    order by s.phoneNumber desc
                    """).list();
            session.getTransaction().commit();
            return list;
    }

    /**
     * Вывести все отсортированные email и телефонные номера
     */
    public List<Object[]> listEmailAndPhoneNumber(Session session) {
            List<Object[]> result = session.createQuery("""
                    select s.email, s.phoneNumber from Suppliers s
                    order by s.email 
                    """).list();
            session.getTransaction().commit();
            return  result;
    }
}
