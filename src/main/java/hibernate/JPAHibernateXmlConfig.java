package hibernate;

import hibernate.dao.StatesDao;
import hibernate.dao.impl.StatesDaoImpl;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAHibernateXmlConfig {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager em = entityManagerFactory.createEntityManager();

        StatesDao statesDao = new StatesDaoImpl((SessionFactory) em);
        statesDao.findAll().forEach(System.out::println);

        em.close();
        entityManagerFactory.close();
    }

}
