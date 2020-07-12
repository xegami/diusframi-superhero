package com.xegami.diusframi.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.xegami.diusframi.model.Appearance;
import com.xegami.diusframi.model.Biography;
import com.xegami.diusframi.model.Connections;
import com.xegami.diusframi.model.Images;
import com.xegami.diusframi.model.Powerstats;
import com.xegami.diusframi.model.Work;

public class Converters {

	@TypeConverter
	public static String toString(Appearance appearance) {
		return new Gson().toJson(appearance);
	}

	@TypeConverter
	public static Appearance toAppearance(String appearance) {
		return new Gson().fromJson(appearance, Appearance.class);
	}

	@TypeConverter
	public static String toString(Biography biography) {
		return new Gson().toJson(biography);
	}

	@TypeConverter
	public static Biography toBiography(String biography) {
		return new Gson().fromJson(biography, Biography.class);
	}

	@TypeConverter
	public static String toString(Connections connections) {
		return new Gson().toJson(connections);
	}

	@TypeConverter
	public static Connections toConnections(String connections) {
		return new Gson().fromJson(connections, Connections.class);
	}

	@TypeConverter
	public static String toString(Images images) {
		return new Gson().toJson(images);
	}

	@TypeConverter
	public static Images toImages(String images) {
		return new Gson().fromJson(images, Images.class);
	}

	@TypeConverter
	public static String toString(Powerstats powerstats) {
		return new Gson().toJson(powerstats);
	}

	@TypeConverter
	public static Powerstats toPowerstats(String powerstats) {
		return new Gson().fromJson(powerstats, Powerstats.class);
	}

	@TypeConverter
	public static String toString(Work work) {
		return new Gson().toJson(work);
	}

	@TypeConverter
	public static Work toWork(String work) {
		return new Gson().fromJson(work, Work.class);
	}

}
