package hibernate.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "states")
public class State {

    @Id
    private String stateId;
    private String stateName;
}
