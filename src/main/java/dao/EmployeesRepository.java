package dao;

import entity.Category;
import entity.Employees;
import entity.Ranks;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.graph.GraphSemantic;
import org.hibernate.graph.RootGraph;
import utils.HibernateUtil;

import java.util.List;

public class EmployeesRepository extends BaseRepository<Long, Employees> {
    private static SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public EmployeesRepository() {
        super(Employees.class, sessionFactory);
    }

    /**
     * Вывести всех работников без учёта менеджеров.
     * Отсортировать по дню рождения
     * Применить graphQl API для формирования единого запроса.
     */
    public List<Employees> findAllEmployeesLessManagers() {
        @Cleanup var session = sessionFactory.openSession();
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
    public String findEmployeesPhoneNumberById(Long employeeId) {
        @Cleanup var session = sessionFactory.openSession();
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
    public Employees changeEmployeeStatus(Long employeeId, Greid status) {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        Employees employee = session.get(Employees.class, employeeId);
        Ranks newEmployeeRank = session.createQuery("""
                        select r from Ranks r
                        where r.rankName = :status
                        """, Ranks.class)
                .setParameter("status", status)//
                .getSingleResult();
        employee.setRanks(newEmployeeRank);

        session.update(employee);
        session.getTransaction().commit();
        return employee;
    }
}

