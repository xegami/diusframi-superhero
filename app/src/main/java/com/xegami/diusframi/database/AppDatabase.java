package com.xegami.diusframi.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.xegami.diusframi.dao.CharacterDao;
import com.xegami.diusframi.model.Character;

@Database(entities = {Character.class}, version = 2, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {

	private static volatile AppDatabase instance;

	public static synchronized AppDatabase getInstance(Context context) {
		if (instance == null) {
			instance = Room
					.databaseBuilder(context, AppDatabase.class, "diusframi.db")
					.allowMainThreadQueries()
					.fallbackToDestructiveMigration()
					.build();
		}

		return instance;
	}

	public abstract CharacterDao characterDao();

}
