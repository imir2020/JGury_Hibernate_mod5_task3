package dao;

import entity.Category;
import org.hibernate.SessionFactory;
import utils.HibernateUtil;


public class CategoryRepository extends BaseRepository<Long, Category>{
    private static SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public CategoryRepository() {
        super(Category.class, sessionFactory);
    }
}
