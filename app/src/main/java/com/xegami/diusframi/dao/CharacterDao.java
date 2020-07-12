package com.xegami.diusframi.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.xegami.diusframi.model.Character;

import java.util.List;

@Dao
public abstract class CharacterDao {

	@Query("SELECT * FROM character")
	public abstract List<Character> findAll();

	@Query("SELECT * from character WHERE id= :id")
	public abstract Character findById(Long id);

	@Update
	public abstract void update(Character... characters);

	@Insert
	public abstract void insert(Character... characters);

	public void insertOrUpdateAll(List<Character> characters) {
		for (Character c : characters) {
			Character dbCharacter = findById(c.getId());

			if (dbCharacter != null) {
				c.setFavorite(dbCharacter.isFavorite());
				update(c);
			} else {
				insert(c);
			}
		}
	}
}
