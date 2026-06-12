package oe.entity;

import java.util.List;

import jakarta.persistence.*;
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


	
	@OneToMany(mappedBy = "video", fetch = FetchType.EAGER)
	List<Favorite> favorites;
	
	@OneToMany(mappedBy = "video")
	List<Share> shares;
}