package dao;

import entity.Suppliers;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.HibernateUtil;

import java.util.List;

public class SuppliersDaoTest {
    private final Logger log = LoggerFactory.getLogger("SuppliersDaoTest");

    @Test
    public void findAll() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        List<Suppliers> list = session.createQuery("from Suppliers", Suppliers.class).list();
        session.getTransaction().commit();
        log.info("List from method findAll(): {}", list);
    }

    @Test
    public void findById() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Suppliers supplier = session.get(Suppliers.class, 3L);
        session.getTransaction().commit();
        log.info("Object from method findById(): {}", supplier);
    }

    @Test
    public void save() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
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
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
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
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Suppliers supplier = session.get(Suppliers.class, 5L);
        session.delete(supplier);
        session.getTransaction().commit();
        log.warn("Object was deleted in method delete(): {}",supplier);
    }
}
