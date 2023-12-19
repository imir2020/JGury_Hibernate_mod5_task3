package dao;

import entity.Category;
import entity.Suppliers;
import utils.HibernateUtil;
import utils.StatementUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SuppliersDao implements Dao<Long, Suppliers> {
    private static final SuppliersDao INSTANCE = new SuppliersDao();

    private SuppliersDao() {
    }

    public static SuppliersDao getInstance() {
        return INSTANCE;
    }


    @Override
    public List<Suppliers> findAll() {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            List<Suppliers> list = session.createQuery("from Suppliers", Suppliers.class).list();
            session.getTransaction().commit();
            return list;
        }
    }

    @Override
    public Optional<Suppliers> findById(Long id) {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Suppliers supplier = session.get(Suppliers.class, id);
            session.getTransaction().commit();
            return Optional.ofNullable(supplier);
        }
    }

    @Override
    public Suppliers save(Suppliers supplier) {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.save(supplier);
            session.getTransaction().commit();
            return null;
        }
    }

    @Override
    public boolean update(Suppliers suppliers) {
        boolean isUpdate = false;
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.update(suppliers);
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
            Suppliers supplier = session.get(Suppliers.class, id);
            session.delete(supplier);
            session.getTransaction().commit();
            isDelete = true;
        }
        return isDelete;
    }
}
