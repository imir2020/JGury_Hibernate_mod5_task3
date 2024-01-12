package dao;

import entity.Orders;
import entity.Suppliers;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.HibernateUtil;
import utils.TestDataImporter;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static junit.framework.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public class OrdersRepositoryTest {
    Logger log = LoggerFactory.getLogger("OrdersDaoTest");

    private static final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
    private final OrderRepository orderRepostory = new OrderRepository();
    private final SuppliersRepository suppliersRepository = new SuppliersRepository();


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
        List<Orders> list = orderRepostory.findAll();
        assertTrue(list.size() > 0);
        log.info("List of orders is: {}", list);
    }

    @Test
    public void findById() {
        Long orderId = 1L;
        Orders order = orderRepostory.findById(orderId).get();
        assertNotNull(order);
        log.info("The order by id is: {}", order);
    }

    @Test
    public void save() {
        Suppliers supplier = Suppliers.builder()
                .name("Tails&&Beaks")
                .address("On the village for grandfather")
                .email("tail@gmail.com")
                .phoneNumber("0-222-999-99-99")
                .build();
        suppliersRepository.save(supplier);
        Orders order = Orders.builder()
                .supplier(supplier)
                .nameProduct("Шоколад")
                .countProduct(2000L)
                .priceProduct(50L)
                .dateOrder(LocalDate.now())
                .build();
        Orders orderAfterSave = orderRepostory.save(order);
        assertEquals(order, orderAfterSave);
    }

    @Test
    public void update() {
        Long orderId = 1L;
        Orders order = orderRepostory.findById(orderId).get();
        assertNotNull(order);
        order.setCountProduct(350L);
        orderRepostory.update(order);
    }

    @Test
    public void delete() {
        Long orderId = 1L;
        assertThrows(NoSuchElementException.class, () -> {
            orderRepostory.delete(orderId);
             orderRepostory.findById(orderId).get();
        });
    }
}
