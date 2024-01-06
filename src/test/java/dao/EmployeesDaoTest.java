package dao;


import entity.Employees;
import entity.Ranks;
import lombok.Cleanup;
import org.hibernate.SessionFactory;
import org.hibernate.graph.RootGraph;
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

import static junit.framework.Assert.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

/**
 * Создать 3 дополнительных метода вызова данных из базы, +  использовать entityGraph API для решения проблемы N + 1
 */

@TestInstance(PER_CLASS)
public class EmployeesDaoTest {
    private static final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
    private final EmployeesDao employeesDao = EmployeesDao.getInstance();

    Logger log = LoggerFactory.getLogger("EmployeesDaoTest");

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
        List<Employees> employees = employeesDao.findAll();
        assertThat(employees).hasSize(11);
        log.info("Result list from method findAll(): {}", employees);
    }

    @Test
    public void findById() {
        Employees employees = employeesDao.findById(6L).get();
        log.info("Object employees from method findById(): {}", employees);
    }

    @Test
    public void save() {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Employees employee = Employees.builder()
                .lastName("Tunov")
                .name("Vadim")
                .middleName("Sadyikov")
                .dateBirth(LocalDate.parse("1990-03-29"))
                .phoneNumber("8-992-456-91-00")
                .address("Vladivostok, veteranov 54,d 6,corp 9, kv 100")
                .build();
        Ranks rank = session.get(Ranks.class, 2L);
        employee.setRanks(rank);
        session.save(employee);
        session.getTransaction().commit();
        log.info("Object from method save() is saved: {}", employee);
    }

    @Test
    public void update() {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Employees employee = session.get(Employees.class, 11L);
        employee.setPhoneNumber("8-992-555-10-00");
        employee.setName("Leonid");
        session.update(employee);
        session.getTransaction().commit();
        log.info("Object from method update() is updated: {}", employee);
    }

    @Test
    public void delete() {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Employees employee = session.get(Employees.class, 10L);
        session.delete(employee);
        session.getTransaction().commit();
        log.warn("Object was deleted in method delete(): {}", employee);
    }

    /**
     * Вывести всех работников без учёта менеджеров.
     * Отсортировать по дню рождения
     */
    @Test
    public void findAllEmployeesLessManagers() {
        @Cleanup var session = sessionFactory.openSession();
        List<Employees> list = employeesDao.findAllEmployeesLessManagers(session);
        list.forEach(System.out::println);
        assertThat(list).hasSize(7);
    }

    /**
     * Найти телефон работника по id
     */
    @Test
    public void findEmployeesPhoneNumberById() {
        @Cleanup var session = sessionFactory.openSession();
        Long employeeId = 2L;

        String phoneNumber = employeesDao.findEmployeesPhoneNumberById(employeeId,session);
        assertEquals("8-925-444-89-17", phoneNumber);
    }

    /**
     * Изменить статус работника на нужный
     */
    @Test
    public void changeEmployeeStatus() {
        @Cleanup var session = sessionFactory.openSession();
        Long employeeId = 6L;
        Employees employee = employeesDao.changeEmployeeStatus(employeeId, Greid.MANAGER,session);
        Employees employeeFromBase = session.get(Employees.class, employeeId);
        assertEquals(employee.getRank().getRankName(), employeeFromBase.getRank().getRankName());
    }
}
