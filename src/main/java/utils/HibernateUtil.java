package utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
   public static SessionFactory buildSessionFactory(){
       Configuration configuration = new Configuration();
       configuration.configure();
       return configuration.buildSessionFactory();
   }
}
