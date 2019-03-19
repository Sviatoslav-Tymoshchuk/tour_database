package hibernate;

import hibernate.dao.AdminDao;
import hibernate.dao.StatesDao;
import hibernate.dao.impl.AdminDaoImpl;
import hibernate.dao.impl.StatesDaoImpl;
import hibernate.model.Admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class Main {


    public static void main(String[] args) {
        HibernateConfig config = new HibernateJavaConfig();
        SessionFactory sessionFactory = config.getSessionFactory();

        StatesDao statesDao = new StatesDaoImpl(sessionFactory);
        System.out.println(statesDao.findAll());

        Admin admin = new Admin();
        admin.setUsername("hibernate_admin");
        admin.setPassword("pass");

        try (Session session = sessionFactory.openSession()) {
            List<Admin> adminList = session.createQuery("from Admin", Admin.class).list();
            adminList.forEach(System.out::println);
        }

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the admin object
            session.save(admin);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        AdminDao adminDao = new AdminDaoImpl(sessionFactory);
        System.out.println("Admin by id: " +adminDao.getAdminById(1));
        //System.out.println("Admin by name: " + adminDao.getAdminByUsername("hibernate_admin"));

        try (Session session = sessionFactory.openSession()) {
            List<Admin> adminList = session.createQuery("from Admin", Admin.class).list();
            adminList.forEach(System.out::println);
        }

        config.shutdown();
    }
}
