package hibernate.dao;

import hibernate.model.Package;

import java.util.List;

public interface PackagesDao {

    List<Package> findAll();

    Package getPackageById(String id);

    boolean insertPackage(Package packAge);

    boolean deletePackage(String id);
}
