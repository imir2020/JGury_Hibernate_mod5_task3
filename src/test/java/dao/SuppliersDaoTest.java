package dao;

import entity.Suppliers;
import lombok.Cleanup;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.HibernateUtil;
import utils.TestDataImporter;

import java.util.List;


/**
 * Создать 3 дополнительных метода вызова данных из базы, +  использовать entityGraph API для решения проблемы N + 1
 */
@TestInstance(PER_CLASS)
public class SuppliersDaoTest {
    private final SuppliersDao suppliersDao = SuppliersDao.getInstance();
    private static final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();


    private final Logger log = LoggerFactory.getLogger("SuppliersDaoTest");

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
        List<Suppliers> list = session.createQuery("from Suppliers", Suppliers.class).list();
        session.getTransaction().commit();
        log.info("List from method findAll(): {}", list);
    }

    @Test
    public void findById() {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Suppliers supplier = session.get(Suppliers.class, 3L);
        session.getTransaction().commit();
        log.info("Object from method findById(): {}", supplier);
    }

    @Test
    public void save() {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Suppliers supplier = Suppliers.builder()
                .name("Tails&&Beaks")
                .address("On the village for grandfather")
                .email("tail@gmail.com")
                .phoneNumber("2-222-333-44-55")
                .build();
        session.save(supplier);
        session.getTransaction().commit();
        log.info("Object was saved in method save(): {}", supplier);
    }

    @Test
    public void update() {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Suppliers supplier = session.get(Suppliers.class, 3L);
        supplier.setAddress("Kitezh");
        session.update(supplier);
        session.getTransaction().commit();
        log.info("Object was updated in method update(): {}", supplier);
    }

    @Test
    public void delete() {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Suppliers supplier = session.get(Suppliers.class, 5L);
        session.delete(supplier);
        session.getTransaction().commit();
        log.warn("Object was deleted in method delete(): {}", supplier);
    }

    @Test
    public void listPhoneNumbers() {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        List<String> results = suppliersDao.listPhoneNumbers(session);
        assertThat(results).hasSize(3);
        results.forEach(System.out::println);
    }

    @Test
    public void listEmailAndPhoneNumbers() {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        List<Object[]> results = suppliersDao.listEmailAndPhoneNumber(session);
        List<String> emailList = results.stream().map(r -> (String) r[0]).toList();
        assertThat(emailList).contains("horn@gmail.com", "beak@gmail.com", "nose@yandex.ru");
        emailList.forEach(System.out::println);

        List<String> phoneNumbersList = results.stream().map(r -> (String) r[1]).toList();
        assertThat(phoneNumbersList).contains("8-988-342-65-98", "1-111-111-11-99", "8-455-876-23-21");
        phoneNumbersList.forEach(System.out::println);
    }
}
