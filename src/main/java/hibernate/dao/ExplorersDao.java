package hibernate.dao;

import hibernate.model.Explorer;

import java.util.List;

public interface ExplorersDao {

    List<Explorer> findAll();

    Explorer getExplorerById(String id);

    boolean insertExplorer(Explorer explorer);

    boolean deleteExplorer(String id);
}
