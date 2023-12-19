package dao;

import entity.Category;
import lombok.Cleanup;
import org.apache.log4j.PropertyConfigurator;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.HibernateUtil;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CategoryDaoTest {
   private final Logger logger = LoggerFactory.getLogger("CategoryDaoTest");

    @Test
    public void findAll() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.getTransaction().begin();
        List<Category> categories = session.createQuery("from Category", Category.class).list();
        System.out.println(categories);//test
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    @Test
    public void findById() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Category category = session.get(Category.class, 2L);
        logger.info("this is category example {}", category);
        session.getTransaction().commit();
    }

    @Test
    public void save() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.getTransaction().begin();
        Category category = Category.builder()
                .categoryName("Питьевая вода")
                .build();
        session.save(category);
        session.getTransaction().commit();
        logger.info("Object was saved in method save(): {}", category);

    }

    @Test
    public void update() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Category category = session.get(Category.class, 3L);
        category.setCategoryName("Колбасы");
        session.update(category);
        session.getTransaction().commit();
    }

    @Test
    public void delete() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Category category = session.get(Category.class, 3L);
        session.delete(category);
        session.getTransaction().commit();
    }

}
