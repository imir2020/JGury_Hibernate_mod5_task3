package dao;

import entity.Suppliers;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateUtil;

import java.util.List;

public class SuppliersRepository extends BaseRepository<Long, Suppliers> {
    private static SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
    public SuppliersRepository() {
        super(Suppliers.class, sessionFactory);
    }

    /**
     * Найти и вывести отсортированный список всех телефонных номеров
     */
    public List<String> listPhoneNumbers() {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
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
    public List<Object[]> listEmailAndPhoneNumber() {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        List<Object[]> result = session.createQuery("""
                    select s.email, s.phoneNumber from Suppliers s
                    order by s.email 
                    """).list();
        session.getTransaction().commit();
        return  result;
    }
}
