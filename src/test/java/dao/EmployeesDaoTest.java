package dao;

import entity.Category;
import entity.Employees;
import entity.Ranks;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.HibernateUtil;

import java.time.LocalDate;
import java.util.List;

public class EmployeesDaoTest {
    Logger log = LoggerFactory.getLogger("EmployeesDaoTest");

    @Test
    public void findAll() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        List<Employees> employees = session.createQuery("FROM Employees").list();
        log.info("Result list from method findAll(): {}", employees);
        session.getTransaction().commit();
    }

    @Test
    public void findById() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Employees employees = session.get(Employees.class, 6L);
        log.info("Object employees from method findById(): {}", employees);
        session.getTransaction().commit();
    }

    @Test
    public void save() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
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
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Employees employee = session.get(Employees.class, 12L);
        employee.setPhoneNumber("8-992-555-10-00");
        employee.setName("Leonid");
        session.update(employee);
        session.getTransaction().commit();
        log.info("Object from method update() is updated: {}", employee);
    }

    @Test
    public void delete() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Employees employee = session.get(Employees.class, 10L);
        session.delete(employee);
        session.getTransaction().commit();
        log.warn("Object was deleted in method delete(): {}",employee);
    }
}
