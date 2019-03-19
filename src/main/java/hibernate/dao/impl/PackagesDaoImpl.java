package hibernate.dao.impl;

import hibernate.dao.PackagesDao;
import hibernate.model.Package;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class PackagesDaoImpl implements PackagesDao {

    private final SessionFactory sessionFactory;

    public PackagesDaoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Package> findAll() {
        try (Session session = sessionFactory.openSession()) {
            List<Package> packageList = session.createQuery("from Package", Package.class).list();
            packageList.forEach(System.out::println);
            return packageList;
        }
    }

    @Override
    public Package getPackageById(String id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Package packAge = session.load(Package.class, id);
            System.out.println(packAge);

            transaction.commit();
            return packAge;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insertPackage(Package packAge) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(packAge);
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
    public boolean deletePackage(String id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(deletePackage(id));
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
