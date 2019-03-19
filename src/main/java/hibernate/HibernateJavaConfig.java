package hibernate;

import hibernate.model.Admin;
import hibernate.model.Package;
import hibernate.model.State;
import hibernate.model.Tour;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateJavaConfig implements HibernateConfig {

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    @Override
    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/public?useSSL=false");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "adminpassword");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MariaDB10Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.FORMAT_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                // Tworzy nową tabelę z każdym wywołaniem programu.
                //settings.put(Environment.HBM2DDL_AUTO, "create-drop");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Admin.class);
                configuration.addAnnotatedClass(State.class);
                configuration.addAnnotatedClass(Tour.class);
                configuration.addAnnotatedClass(Package.class);


                serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    @Override
    public void shutdown() {
        StandardServiceRegistryBuilder.destroy(serviceRegistry);
    }
}
