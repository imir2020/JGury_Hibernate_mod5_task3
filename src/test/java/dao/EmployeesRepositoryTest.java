package dao;


import entity.Employees;
import entity.Ranks;
import lombok.Cleanup;
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
import java.util.Optional;

import static junit.framework.Assert.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;


@TestInstance(PER_CLASS)
public class EmployeesRepositoryTest {
    private static final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
    private final EmployeesRepository employeesRepository = new EmployeesRepository();
    private final RanksRepository ranksRepository = new RanksRepository();

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
        List<Employees> employees = employeesRepository.findAll();
        employees.forEach(System.out::println);
        assertThat(employees).hasSize(11);
        log.info("Result list from method findAll(): {}", employees);
    }

    @Test
    public void findById() {
        Employees employees = employeesRepository.findById(6L).get();
        log.info("Object employees from method findById(): {}", employees);
    }

    @Test
    public void save() {
        Ranks rank = ranksRepository.findById(2L).get();
        Employees employee = Employees.builder()
                .lastName("Artamonov")
                .name("Alex")
                .middleName("Sadyikov")
                .dateBirth(LocalDate.parse("1980-03-29"))
                .phoneNumber("8-654-333-98-11")
                .address("Torsk, veteranov 54,dom 45,corp 11, kv 67")
                .build();
        employee.setRanks(rank);
        employeesRepository.save(employee);
        log.info("Object from method save() is saved: {}", employee);
    }

    @Test
    public void update() {
        @Cleanup var session = sessionFactory.openSession();
        Long id = 11L;
        Employees employee = session.get(Employees.class, id);
        employee.setPhoneNumber("8-992-555-10-00");
        employee.setName("Leonid");

        employeesRepository.update(employee);
        Employees getEmployee = session.get(Employees.class, id);
        assertEquals(employee, getEmployee);
        log.info("Object from method update() is updated: {}", employee);
    }

    @Test
    public void delete() {
        employeesRepository.delete(11L);
        Optional<String> employee = employeesRepository.findById(11L).ofNullable("This element was deleted");
        log.warn("Object was deleted in method delete(): {}", employee.get());
    }

    /**
     * Вывести всех работников без учёта менеджеров.
     * Отсортировать по дню рождения
     */
    @Test
    public void findAllEmployeesLessManagers() {
        List<Employees> list = employeesRepository.findAllEmployeesLessManagers();
        list.forEach(System.out::println);
        assertThat(list).hasSize(7);
    }

    /**
     * Найти телефон работника по id
     */
    @Test
    public void findEmployeesPhoneNumberById() {
        Long employeeId = 2L;

        String phoneNumber = employeesRepository.findEmployeesPhoneNumberById(employeeId);
        assertEquals("8-925-444-89-17", phoneNumber);
        System.out.println(phoneNumber);
    }

    /**
     * Изменить статус работника на нужный
     */
    @Test
    public void changeEmployeeStatus() {
        Long employeeId = 6L;
        Employees employee = employeesRepository.changeEmployeeStatus(employeeId, Greid.MANAGER);
        Employees employeeFromBase = employeesRepository.findById(employeeId).get();
        assertEquals(employee.getRank().getRankName(), employeeFromBase.getRank().getRankName());
    }
}
