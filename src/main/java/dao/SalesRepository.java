package dao;

import entity.Sales;
import org.hibernate.SessionFactory;

public class SalesRepository extends BaseRepository<Long, Sales> {
    public SalesRepository( SessionFactory sessionFactory) {
        super(Sales.class, sessionFactory);
    }
}
