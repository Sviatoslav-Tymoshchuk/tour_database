package hibernate.dao;

import hibernate.model.State;

import java.util.List;

public interface StatesDao {

    List<State> findAll();

    State getStateById(String id);

    boolean insertState(State state);

    boolean deleteState(String id);
}
