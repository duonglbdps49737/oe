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

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

@Entity
@Table(name = "Videos")
public class Video {
	@Id
	String id;
	String title;
	String poster;
	String description;
	int views;
	boolean active;
	
	@OneToMany(mappedBy = "video")
	List<Favorite> favorites;
	
	@OneToMany(mappedBy = "video")
	List<Share> shares;
}