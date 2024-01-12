package dao;

import entity.Category;
import entity.Products;
import entity.Suppliers;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.HibernateUtil;

import java.util.List;

public class ProductsRepositoryTest {
    private final Logger log = LoggerFactory.getLogger("ProductsDaoTest");

    @Test
    public void findAll() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        List<Products> products = session.createQuery("from Products", Products.class).list();
        session.getTransaction().commit();
        log.info("This is list of products from method findAll(): {}", products);
    }

    @Test
    public void findById() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Products product = session.get(Products.class, 1L);
        session.getTransaction().commit();
        log.info("Object from method findBuId(): {}", product);
    }

    @Test
    public void save() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Products product = Products.builder()
                .name("Мясо")
                .count(125L)
                .priceForOne(500L)
                .build();
//        Suppliers supplier = Suppliers.builder()
//                .name("Beaks&&Feathers")
//                .address("Unknown")
//                .email("beak@gmail.com")
//                .phoneNumber("1-111-111-11-99")
//                .build();
        //session.save(supplier);
        Category category = session.get(Category.class, 3L);
        Suppliers supplier = session.get(Suppliers.class,4L);
        product.setSupplier(supplier);
        product.setCategory(category);
        session.save(product);
        session.getTransaction().commit();
        log.info("Object was saved in method save(): {}", product);
    }

    @Test
    public void update() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Products product = session.get(Products.class, 1L);
        product.setCount(700L);
        session.update(product);
        session.getTransaction().commit();
        log.info("Object was updated in method update(): {}", product);
    }

    @Test
    public void delete() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Products product = session.get(Products.class, 4L);
        session.delete(product);
        session.getTransaction().commit();
        log.warn("Object was deleted in method delete():{}",product);
    }
}
