package utils;

import org.hibernate.SessionFactory;

public class DataLoader {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        TestDataImporter.importData(sessionFactory);
    }
}
