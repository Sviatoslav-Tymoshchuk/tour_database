package hibernate.dao.impl;

import hibernate.dao.ExplorersDao;
import hibernate.model.Explorer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ExplorersDaoImpl implements ExplorersDao {

    private final SessionFactory sessionFactory;

    public ExplorersDaoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Explorer> findAll() {
        try (Session session = sessionFactory.openSession()) {
            List<Explorer> explorerList = session.createQuery("from Explorer", Explorer.class).list();
            explorerList.forEach(System.out::println);
            return explorerList;
        }
    }

    @Override
    public Explorer getExplorerById(String id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Explorer explorer = session.load(Explorer.class, id);
            System.out.println(explorer);

            transaction.commit();
            return explorer;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insertExplorer(Explorer explorer) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(explorer);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteExplorer(String id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(deleteExplorer(id));
            transaction.commit();
            return true;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
