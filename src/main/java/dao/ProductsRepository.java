package dao;

import entity.Products;
import lombok.Cleanup;
import org.hibernate.SessionFactory;
import utils.HibernateUtil;

public class ProductsRepository extends BaseRepository<Long, Products> {
    private static SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public ProductsRepository() {
        super(Products.class, sessionFactory);
    }
}
