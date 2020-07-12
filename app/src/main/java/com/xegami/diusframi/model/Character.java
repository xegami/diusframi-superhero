package com.xegami.diusframi.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
public class Character implements Serializable {

	@PrimaryKey
	private Long id;
	private String name;
	private String slug;
	private Powerstats powerstats;
	private Appearance appearance;
	private Biography biography;
	private Work work;
	private Connections connections;
	private Images images;
	private boolean isFavorite;
}
