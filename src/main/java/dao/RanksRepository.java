package dao;

import entity.Ranks;
import org.hibernate.SessionFactory;
import utils.HibernateUtil;

public class RanksRepository extends BaseRepository<Long, Ranks> {
    private static SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public RanksRepository() {
        super(Ranks.class, sessionFactory);
    }
}
