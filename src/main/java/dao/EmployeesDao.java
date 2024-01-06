package dao;


import entity.Employees;
import entity.Ranks;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.graph.GraphSemantic;
import org.hibernate.graph.RootGraph;
import org.hibernate.graph.SubGraph;
import utils.HibernateUtil;

import java.util.List;
import java.util.Optional;

import static junit.framework.Assert.assertEquals;

/**
 * Создать 3 дополнительных метода вызова данных из базы, +  использовать entityGraph API для решения проблемы N + 1
 */

public class EmployeesDao implements Dao<Long, Employees> {
    private static final EmployeesDao INSTANCE = new EmployeesDao();
    private final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    private EmployeesDao() {
    }

    public static EmployeesDao getInstance() {
        return INSTANCE;
    }

    /**
     * Применить graphQl API для оптимизации запроса
     */
    @Override
    public List<Employees> findAll() {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            RootGraph<Employees> employeesGraph = session.createEntityGraph(Employees.class);
            employeesGraph.addAttributeNodes("rank");
            List<Employees> employees = session.createQuery("""
                    select e from Employees e
                    """)
                    .setHint(GraphSemantic.LOAD.getJpaHintName(), employeesGraph)
                    .list();
            session.getTransaction().commit();
            return employees;
        }
    }

    @Override
    public Optional<Employees> findById(Long id) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            Employees employee = session.get(Employees.class, id);
            session.getTransaction().commit();
            return Optional.ofNullable(employee);
        }
    }

    @Override
    public Employees save(Employees employee) {
        try (var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.save(employee);
            session.getTransaction().commit();
            return employee;
        }
    }

    @Override
    public boolean update(Employees employee) {
        boolean isUpdate = false;
        try (var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.update(employee);
            session.getTransaction().commit();
            isUpdate = true;
        }
        return isUpdate;
    }

    @Override
    public boolean delete(Long id) {
        boolean isDelete = false;
        try (var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Employees employee = session.get(Employees.class, id);
            session.delete(employee);
            session.getTransaction().commit();
            isDelete = true;
        }
        return isDelete;
    }

    /**
     * Вывести всех работников без учёта менеджеров.
     * Отсортировать по дню рождения
     * Применить graphQl API для формирования единого запроса.
     */
    public List<Employees> findAllEmployeesLessManagers(Session session) {
        session.beginTransaction();

        RootGraph<Employees> employeesRootGraph = session.createEntityGraph(Employees.class);
        employeesRootGraph.addAttributeNodes("rank");
        List<Employees> result = session.createQuery("""
                select e from Employees e
                where e.rank.id = 2
                order by e.dateBirth
                """)
                .setHint(GraphSemantic.LOAD.getJpaHintName(), employeesRootGraph)
                .list();

        session.getTransaction().commit();
        return result;
    }


    /**
     * Найти телефон работника по id
     */
    public String findEmployeesPhoneNumberById(Long employeeId, Session session) {
        session.beginTransaction();
        String phoneNumber = (String) session.createQuery("""
                        select e.phoneNumber from Employees e
                        where e.id = :id
                        """)
                .setParameter("id", employeeId)
                .getSingleResult();
        session.getTransaction().commit();
        return phoneNumber;
    }

    /**
     * Изменить статус работника на нужный.
     */
    public Employees changeEmployeeStatus(Long employeeId, Greid status, Session session) {
        session.beginTransaction();
        Employees employee = session.get(Employees.class, employeeId);
        Ranks newEmployeeRank = session.createQuery("""
                        select r from Ranks r
                        where r.rankName = :status
                        """,Ranks.class)
                .setParameter("status", status)//
                .getSingleResult();
        employee.setRanks(newEmployeeRank);

        session.update(employee);
        session.getTransaction().commit();
        return employee;
    }
}
