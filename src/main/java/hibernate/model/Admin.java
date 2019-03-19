package hibernate.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NamedQuery(name = "Admin_findByUsername", query = "from Admin where username =: username")
@Entity
@Table(name = "admins")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Size(max = 50 )
	private String username;
	@NotNull
	@Size(max = 255 )
	private String password;
}
