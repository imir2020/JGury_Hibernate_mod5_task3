package dao;


import entity.Employees;
import utils.HibernateUtil;
import java.util.List;
import java.util.Optional;

public class EmployeesDao implements Dao<Long, Employees> {
    private static final EmployeesDao INSTANCE = new EmployeesDao();

    private EmployeesDao() {
    }

    public static EmployeesDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Employees> findAll() {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.beginTransaction();
            List<Employees> employees = session.createQuery("from Employees", Employees.class).list();
            session.getTransaction().commit();
            return employees;
        }
    }

    @Override
    public Optional<Employees> findById(Long id) {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Employees employee = session.get(Employees.class, id);
            session.getTransaction().commit();
            return Optional.ofNullable(employee);
        }
    }

    @Override
    public Employees save(Employees employee) {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.save(employee);
            session.getTransaction().commit();
            return employee;
        }
    }

    @Override
    public boolean update(Employees employee) {
        boolean isUpdate = false;
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
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
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Employees employee = session.get(Employees.class, id);
            session.delete(employee);
            session.getTransaction().commit();
            isDelete = true;
        }
        return isDelete;
    }
}
