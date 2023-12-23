package dao;

import entity.Products;
import utils.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class ProductsDao implements Dao<Long, Products> {
    private static final ProductsDao INSTANCE = new ProductsDao();

    private ProductsDao() {
    }

    public static ProductsDao getInstance() {
        return INSTANCE;
    }


    @Override
    public List<Products> findAll() {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            List<Products> list = session.createQuery("from Products", Products.class).list();
            session.getTransaction().commit();
            return list;
        }
    }

    @Override
    public Optional<Products> findById(Long id) {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Products product = session.get(Products.class, id);
            session.getTransaction().commit();
            return Optional.ofNullable(product);
        }
    }

    @Override
    public Products save(Products products) {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.save(products);
            session.getTransaction().commit();
            return products;
        }
    }

    @Override
    public boolean update(Products products) {
        boolean isUpdate = false;
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.update(products);
            session.getTransaction().commit();
            isUpdate = true;
        }
        return isUpdate;
    }

    @Override
    public boolean delete(Long id) {
        boolean isDelete = true;
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Products product = session.get(Products.class, id);
            session.delete(product);

            session.getTransaction().commit();

        }catch (Exception e){
            isDelete = false;
            throw new RuntimeException(e);
        }
        return isDelete;
    }
}
