package hibernate.dao.impl;

import hibernate.dao.ToursDao;
import hibernate.model.Package;
import hibernate.model.Tour;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public class ToursDaoImpl implements ToursDao {

    private final SessionFactory sessionFactory;

    public ToursDaoImpl (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Tour> findAll() {
        try (Session session = sessionFactory.openSession()) {
            List<Tour> tourList = session.createQuery("from Tour", Tour.class).list();
            tourList.forEach(System.out::println);
            return tourList;
        }
    }


    @Override
    public Tour getTourById(String id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Tour tour = session.load(Tour.class, id);
            System.out.println(tour);

            transaction.commit();
            return tour;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insertTour(Tour tour) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(tour);
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
    public boolean deleteTour(String id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(deleteTour(id));
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
    public List<Tour> getToursByPackageId(int packageId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Tour> query = session.createQuery("FROM Tour tour WHERE tour.packageId.id = :id", Tour.class);
            query.setParameter("id", packageId);
            List<Tour> tours = query.list();
            tours.forEach(System.out::println);
            return tours;
        }
    }


}
