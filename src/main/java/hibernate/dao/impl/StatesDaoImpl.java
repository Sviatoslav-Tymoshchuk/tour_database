package hibernate.dao.impl;

import hibernate.dao.StatesDao;
import hibernate.model.State;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.util.List;

public class StatesDaoImpl implements StatesDao {

    private final SessionFactory sessionFactory;


    public StatesDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<State> findAll() {
        try (Session session = sessionFactory.openSession()) {
            List<State> stateList = session.createQuery("from State", State.class).list();
            stateList.forEach(System.out::println);
            return stateList;
        }
    }

    @Override
    public State getStateById(String id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            State state = session.load(State.class, id);
            System.out.println(state);

            transaction.commit();
            return state;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insertState(State state) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(state);
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
    public boolean deleteState(String id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(deleteState(id));
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
