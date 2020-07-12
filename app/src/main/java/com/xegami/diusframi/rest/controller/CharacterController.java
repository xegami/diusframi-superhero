package com.xegami.diusframi.rest.controller;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.xegami.diusframi.rest.RetrofitInstance;
import com.xegami.diusframi.model.Character;
import com.xegami.diusframi.rest.event.CharactersEvent;
import com.xegami.diusframi.rest.operation.CharacterOperations;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterController extends BaseController {

	private static CharacterController instance;
	private CharacterOperations chatOperations = RetrofitInstance.getInstance().create(CharacterOperations.class);

	private CharacterController() {

	}

	public synchronized static CharacterController getInstance() {
		if (instance == null) {
			instance = new CharacterController();
		}

		return instance;
	}

	public void findAll() {
		final CharactersEvent event = new CharactersEvent();

		chatOperations.findAll().enqueue(new Callback<JsonElement>() {
			@Override
			public void onResponse(@NonNull Call<JsonElement> call,
								   @NonNull Response<JsonElement> response) {

				if (response.isSuccessful()) {
					JsonArray array = response.body().getAsJsonArray();
					List<Character> characters = Arrays.asList(new Gson().fromJson(array, Character[].class));
					event.setCharacters(characters);

				} else {
					event.setError("Error trying to get the data.");
				}

				event.post();
			}

			@Override
			public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
				onFailureEvent(event);
			}
		});
	}

}
