package com.xegami.diusframi.adapter;

import android.widget.ToggleButton;

import com.xegami.diusframi.model.Character;

public interface CharactersListener {

	void onFavoriteClick(ToggleButton toggleButton, Character character);

	void onItemClick(Character character);

}
