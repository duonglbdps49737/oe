package oe.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

@Entity
@Table(name = "Users")
public class User {
	@Id
	String email;
	String password;
	String fullname;
	boolean admin;
	boolean enabled;
	
	@OneToMany(mappedBy = "user")
	List<Favorite> favorites;
	
	@OneToMany(mappedBy = "user")
	List<Share> shares;
}
