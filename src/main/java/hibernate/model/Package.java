package hibernate.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "packages")

public class Package {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "packageid")

    private int id;

    @Column(name = "packagetitle")

    private String title;

    @Column(name = "packagedescription")

    private String description;

    @Column(name = "packagegraphic")

    private String graphic;
}
