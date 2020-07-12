package com.xegami.diusframi.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.xegami.diusframi.R;
import com.xegami.diusframi.adapter.CharactersListener;
import com.xegami.diusframi.adapter.CharactersRVAdapter;
import com.xegami.diusframi.database.AppDatabase;
import com.xegami.diusframi.model.Character;
import com.xegami.diusframi.rest.controller.CharacterController;
import com.xegami.diusframi.rest.event.CharactersEvent;
import com.xegami.diusframi.util.InternetUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements CharactersListener {

	private List<Character> characters = new ArrayList<>();
	private CharactersRVAdapter adapter;
	private RecyclerView rvCharacters;
	private boolean showFavorites = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		setSupportActionBar(findViewById(R.id.toolbar));
		setTitle(getString(R.string.list_title));

		setUpAdapter();

		SearchView svFilter = findViewById(R.id.sv_activity_home_filter);
		svFilter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String s) {
				adapter.filterByName(s);
				return true;
			}

			@Override
			public boolean onQueryTextChange(String s) {
				adapter.filterByName(s);
				return true;
			}
		});

		if (InternetUtils.isNetworkAvailable(this)) {
			CharacterController.getInstance().findAll();
		} else {
			notifyAdapter();
		}

		FloatingActionButton fab = findViewById(R.id.fab_activity_home_favorites);
		fab.setOnClickListener(v -> {
			showFavorites = !showFavorites;
			adapter.filterByFavorites(showFavorites);
			if (showFavorites) {
				fab.setImageResource(R.drawable.ic_clear);
			} else {
				fab.setImageResource(R.drawable.ic_star_full);
			}
		});
	}

	private void setUpAdapter() {
		rvCharacters = findViewById(R.id.rv_activity_home_characters);
		rvCharacters.setLayoutManager(new LinearLayoutManager(this));
		adapter = new CharactersRVAdapter(characters, this);
		rvCharacters.setAdapter(adapter);
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onEvent(CharactersEvent event) {
		if (event.getError().isEmpty()) {
			AppDatabase.getInstance(this).characterDao().insertOrUpdateAll(event.getCharacters());
			notifyAdapter();
		}
	}

	private void notifyAdapter() {
		characters.clear();
		characters.addAll(AppDatabase.getInstance(this).characterDao().findAll());

		if (characters.size() == 0) {
			findViewById(R.id.tv_activity_home_no_data).setVisibility(View.VISIBLE);
			rvCharacters.setVisibility(View.GONE);
		}

		adapter.notifyDataSetChanged();
	}

	@Override
	public void onFavoriteClick(ToggleButton toggleButton, Character character) {
		character.setFavorite(toggleButton.isChecked());
		AppDatabase.getInstance(this).characterDao().update(character);
		notifyAdapter();
	}

	@Override
	public void onItemClick(Character character) {
		startActivity(new Intent(this, DetailsActivity.class).putExtra("character", character));
	}

	@Override
	public void onStart() {
		super.onStart();
		EventBus.getDefault().register(this);
	}

	@Override
	public void onStop() {
		EventBus.getDefault().unregister(this);
		super.onStop();
	}

	@Override
	protected void onResume() {
		super.onResume();

		notifyAdapter();
	}
}