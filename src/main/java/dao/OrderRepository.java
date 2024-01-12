package dao;

import entity.Orders;
import org.hibernate.SessionFactory;
import utils.HibernateUtil;

public class OrderRepository extends BaseRepository<Long, Orders>{
    private static SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public OrderRepository() {
        super(Orders.class, sessionFactory);
    }
}
