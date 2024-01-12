package dao;

import entity.Suppliers;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.HibernateUtil;
import utils.TestDataImporter;

import javax.persistence.PersistenceException;
import java.util.List;


/**
 * Создать 3 дополнительных метода вызова данных из базы, + использовать entityGraph API для решения проблемы N + 1
 */
@TestInstance(PER_CLASS)
public class SuppliersRepositoryTest {
    private final Logger log = LoggerFactory.getLogger("SuppliersDaoTest");
    private static final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
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
        List<Suppliers> list = suppliersRepository.findAll();
        log.info("List from method findAll(): {}", list);
    }

    @Test
    public void findById() {
        Long supplierId = 3L;
        Suppliers supplier = suppliersRepository.findById(supplierId).get();
        assertNotNull(supplier);
        log.info("Object from method findById(): {}", supplier);
    }

    @Test
    public void save() {
        Suppliers supplier = Suppliers.builder()
                .name("Tails&&Beaks")
                .address("On the village for grandfather")
                .email("tail@gmail.com")
                .phoneNumber("2-222-333-44-55")
                .build();
        Suppliers supplierAfterSave = suppliersRepository.save(supplier);
        assertEquals(supplierAfterSave, supplier);
        log.info("Object was saved in method save(): {}", supplierAfterSave);
    }

    @Test
    public void update() {
        Long supplierId = 3L;
        Suppliers supplier = suppliersRepository.findById(supplierId).get();
        supplier.setEmail("qqq@f.to");
        supplier.setAddress("Kitezh");
        suppliersRepository.update(supplier);
        log.info("Object was updated in method update(): {}", supplier);
    }

    @Test
    public void delete() {
        Long supplierId = 1L;
        assertThrows(PersistenceException.class, () -> {
            suppliersRepository.delete(supplierId);
            suppliersRepository.findById(supplierId);
        });
        log.warn("Object was deleted in method delete()");
    }

    @Test
    public void listPhoneNumbers() {
        List<String> results = suppliersRepository.listPhoneNumbers();
        assertThat(results).hasSize(3);
        results.forEach(System.out::println);
    }

    @Test
    public void listEmailAndPhoneNumbers() {
        List<Object[]> results = suppliersRepository.listEmailAndPhoneNumber();
        List<String> emailList = results.stream().map(r -> (String) r[0]).toList();
        assertThat(emailList).contains("horn@gmail.com", "beak@gmail.com", "nose@yandex.ru");
        emailList.forEach(System.out::println);

        List<String> phoneNumbersList = results.stream().map(r -> (String) r[1]).toList();
        assertThat(phoneNumbersList).contains("8-988-342-65-98", "1-111-111-11-99", "8-455-876-23-21");
        phoneNumbersList.forEach(System.out::println);
    }
}
