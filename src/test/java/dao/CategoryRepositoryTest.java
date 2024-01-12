package dao;

import entity.Category;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.HibernateUtil;
import utils.TestDataImporter;

import java.util.List;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

/**
 * Создать 3 дополнительных метода вызова данных из базы, +  использовать entityGraph API для решения проблемы N + 1
 */
@TestInstance(PER_CLASS)
public class CategoryRepositoryTest {
    private final Logger logger = LoggerFactory.getLogger("CategoryDaoTest");

    private static final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
    private final CategoryRepository categoryRepository = new CategoryRepository();


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
        List<Category> categories = categoryRepository.findAll();
        categories.forEach(System.out::println);
        logger.info("Result list from method findAll(): {}", categories);
    }


    @Test
    public void findById() {
        Category category = categoryRepository.findById(2L).get();
        System.out.println(category + " method FindById()");
        logger.info("this is category example {}", category);
    }

    @Test
    public void save() {
        Category category = Category.builder()
                .categoryName("Натуральные соки")
                .build();
       categoryRepository.save(category);
        logger.info("Object was saved in method save(): {}", category);

    }

    @Test
    public void update() {
        Category category = Category.builder()
                .id(4L)
                .categoryName("Unknown_111")
                .build();
        categoryRepository.update(category);
    }

    @Test
    public void delete() {
        categoryRepository.delete(4L);
    }

}
