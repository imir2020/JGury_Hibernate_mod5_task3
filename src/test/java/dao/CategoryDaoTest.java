package dao;

import entity.Category;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.HibernateUtil;

import java.util.List;

/**
 * Создать 3 дополнительных метода вызова данных из базы, +  использовать entityGraph API для решения проблемы N + 1
 */

public class CategoryDaoTest {
   private final Logger logger = LoggerFactory.getLogger("CategoryDaoTest");

    @Test
    public void findAll() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.getTransaction().begin();
        List<Category> categories = session.createQuery("from Category", Category.class).list();
        session.getTransaction().commit();
    }

    @Test
    public void findById() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Category category = session.get(Category.class, 2L);
        session.getTransaction().commit();
        logger.info("this is category example {}", category);
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
