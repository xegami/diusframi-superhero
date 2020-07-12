package com.xegami.diusframi.rest;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

	private static final String BASE_URL = "https://cdn.rawgit.com/akabab/superhero-api/0.2.0/api/"; // global

	private static Retrofit instance;

	private RetrofitInstance() {
	}

	public static Retrofit getInstance() {
		if (instance == null) {
			instance = buildRetrofit();
		}
		return instance;
	}

	private static Retrofit buildRetrofit() {
		return new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.client(new OkHttpClient.Builder().build())
				.addConverterFactory(GsonConverterFactory.create())
				.build();
	}
}
