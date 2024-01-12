package dao;

import entity.Employees;
import entity.Products;
import entity.Sales;
import lombok.Cleanup;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import utils.HibernateUtil;
import utils.TestDataImporter;

import java.time.LocalDate;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public class SalesRepositoryTest {

    private static final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    private final SalesRepository salesRepository = new SalesRepository(sessionFactory);
    private final ProductsRepository productsRepository = new ProductsRepository();
    private final EmployeesRepository employeesRepository = new EmployeesRepository();


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
        List<Sales> list = salesRepository.findAll();
        list.forEach(System.out::println);
        assertTrue(list.size() > 0);

    }

    @Test
    public void findById() {
        Sales sales = salesRepository.findById(1L).get();
        assertTrue(sales!=null);
        System.out.println(sales);
    }

    @Test
    public void save() {
        Employees employee = employeesRepository.findById(1L).get();
        Products product = productsRepository.findById(1L).get();
        Sales sales = Sales.builder()
                .product(product)
                .count(5L)
                .employee(employee)
                .dateSales(LocalDate.now())
                .build();
        salesRepository.save(sales);
    }

    @Test
    public void update() {
        @Cleanup var session = sessionFactory.openSession();
        Sales sales = session.get(Sales.class, 1L);
        sales.setCount(15L);
        salesRepository.update(sales);
        Sales beforeApdate = session.get(Sales.class, 1L);
        assertTrue(sales.equals(beforeApdate));

    }

    @Test
    public void delete() {
        salesRepository.delete(1L);
    }
}
