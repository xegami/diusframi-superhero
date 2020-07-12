package com.xegami.diusframi.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;

import com.xegami.diusframi.R;
import com.xegami.diusframi.database.AppDatabase;
import com.xegami.diusframi.model.Character;

public class DetailsActivity extends AppCompatActivity {

	private ViewDataBinding binding;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Character character = (Character) getIntent().getSerializableExtra("character");
		binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
		binding.setVariable(BR.character, character);

		setSupportActionBar(findViewById(R.id.toolbar));
		setTitle(character.getName());
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		ToggleButton toggleButton = findViewById(R.id.tbtn_activity_details_favorite);
		toggleButton.setOnClickListener(v -> {
					character.setFavorite(toggleButton.isChecked());
					AppDatabase.getInstance(this).characterDao().update(character);
				}
		);
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				super.onBackPressed();
				return true;

			default:
				return super.onOptionsItemSelected(item);
		}
	}
}