package dao;

import entity.Category;
import entity.Employees;
import entity.Ranks;
import utils.ConnectionManager;
import utils.HibernateUtil;
import utils.StatementUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RanksDao implements Dao<Long, Ranks> {
    private static final RanksDao INSTANCE = new RanksDao();

    private RanksDao() {
    }

    public static RanksDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Ranks> findAll() {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            List<Ranks> list = session.createQuery("from Ranks", Ranks.class).list();
            session.getTransaction().commit();
            return list;
        }
    }

    @Override
    public Optional<Ranks> findById(Long id) {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Ranks rank = session.get(Ranks.class, id);
            session.getTransaction().commit();
            return Optional.ofNullable(rank);
        }
    }

    @Override
    public Ranks save(Ranks rank) {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.save(rank);
            session.getTransaction().commit();
            return rank;
        }
    }

    @Override
    public boolean update(Ranks rank) {
        boolean isUpdate = false;
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.update(rank);
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
            Ranks rank = session.get(Ranks.class, id);
            session.delete(rank);
            session.getTransaction().commit();
            isDelete = true;
        }
        return isDelete;
    }
}
