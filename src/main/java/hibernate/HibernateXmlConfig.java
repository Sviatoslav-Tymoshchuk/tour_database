package hibernate;

import hibernate.model.Admin;
import hibernate.model.Package;
import hibernate.model.State;
import hibernate.model.Tour;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateXmlConfig implements HibernateConfig {

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    @Override
    public SessionFactory getSessionFactory() {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        config.addAnnotatedClass(Admin.class);
        config.addAnnotatedClass(State.class);
        config.addAnnotatedClass(Tour.class);
        config.addAnnotatedClass(Package.class);

        // Since Hibernate Version 4.x, ServiceRegistry Is Being Used
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();

        // Creating Hibernate SessionFactory Instance
        sessionFactory = config.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }

    @Override
    public void shutdown() {
        StandardServiceRegistryBuilder.destroy(serviceRegistry);
    }
}
