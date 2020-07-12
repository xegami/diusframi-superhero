package com.xegami.diusframi.rest.operation;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CharacterOperations {

	@GET("all.json")
	Call<JsonElement> findAll();

}
