package hibernate;

import org.hibernate.SessionFactory;

public interface HibernateConfig {

    SessionFactory getSessionFactory();

    void shutdown();
}
