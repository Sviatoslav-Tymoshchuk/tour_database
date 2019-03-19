package hibernate.model;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@Table(name = "explorers")

public class Explorer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "explorerid")
    private int id;

    @Size(max = 50 )
    private String firstName;

    @Size(max = 50 )
    private String lastName;

    @FutureOrPresent
    private Date dob;

    @Email
    @Size(max = 100 )
    private String email;

    @Size(max = 100)
    private String address;

    @Size(max = 50 )
    private String city;

    @Size(max = 2 )
    private String stateId;

    @Pattern(regexp = "^[0-9]{5}")
    @Size(max = 2 )
    private String zipCode;


    private String username;
    private String password;
    private String tours;
    private String bio;


}
