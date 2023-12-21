package dao;

import entity.Category;
import entity.Employees;
import entity.Sales;
import utils.ConnectionManager;
import utils.HibernateUtil;
import utils.StatementUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SalesDao implements Dao<Long, Sales> {
    private static final SalesDao INSTANCE = new SalesDao();

    private SalesDao() {
    }

    public static SalesDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Sales> findAll() {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            List<Sales> list = session.createQuery("from Sales", Sales.class).list();
            session.getTransaction().commit();
            return list;
        }
    }

    @Override
    public Optional<Sales> findById(Long id) {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Sales sale = session.get(Sales.class, id);
            session.getTransaction().commit();
            return Optional.ofNullable(sale);
        }
    }

    @Override
    public Sales save(Sales sale) {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.save(sale);
            session.getTransaction().commit();
            return sale;
        }
    }

    @Override
    public boolean update(Sales sale) {
        boolean isUpdate = false;
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.update(sale);
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
            Sales sale = session.get(Sales.class, id);
            session.delete(sale);
            session.getTransaction().commit();
        }
        return isDelete;
    }
}
