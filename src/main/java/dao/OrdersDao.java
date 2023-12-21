package dao;


import entity.Orders;
import utils.HibernateUtil;
import java.util.List;
import java.util.Optional;

public class OrdersDao implements Dao<Long, Orders> {
    private static final OrdersDao INSTANCE = new OrdersDao();

    private OrdersDao() {
    }

    public static OrdersDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Orders> findAll() {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            List<Orders> list = session.createQuery("from Orders", Orders.class).list();
            session.getTransaction().commit();
            return list;
        }
    }

    @Override
    public Optional<Orders> findById(Long id) {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Orders order = session.get(Orders.class, id);
            session.getTransaction().commit();
            return Optional.ofNullable(order);
        }
    }

    @Override
    public Orders save(Orders order) {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.save(order);
            session.getTransaction().commit();
            return order;
        }
    }

    @Override
    public boolean update(Orders order) {
        boolean isUpdate = false;
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.update(order);
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
            Orders order = session.get(Orders.class, id);
            session.delete(order);
            session.getTransaction().commit();
            isDelete = true;
        }
        return isDelete;
    }
}
