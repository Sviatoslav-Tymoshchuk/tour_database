package hibernate.dao;

import hibernate.model.Package;
import hibernate.model.Tour;

import java.util.List;

public interface ToursDao {

    List<Tour> findAll();

    Tour getTourById(String id);

    boolean insertTour(Tour tour);

    boolean deleteTour(String id);

    List<Tour> getToursByPackageId(int packageEntity);


}
