package com.xegami.diusframi.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.xegami.diusframi.BR;
import com.xegami.diusframi.R;
import com.xegami.diusframi.model.Character;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CharactersRVAdapter extends RecyclerView.Adapter<CharactersRVAdapter.CharacterViewHolder> {

	private List<Character> characters;
	private List<Character> charactersCopy;
	private CharactersListener listener;

	public CharactersRVAdapter(List<Character> characters, CharactersListener charactersListener) {
		this.characters = characters;
		charactersCopy = characters;
		listener = charactersListener;
	}

	public void filterByName(final String query) {
		characters = new ArrayList<>();

		if (!query.isEmpty()) {
			for (Character c : charactersCopy) {
				if (c.getName().toLowerCase().contains(query)) characters.add(c);
			}
		} else {
			characters.addAll(charactersCopy);
		}

		notifyDataSetChanged();
	}

	public void filterByFavorites(boolean showFavorites) {
		characters = new ArrayList<>();

		if (showFavorites) {
			for (Character c : charactersCopy) {
				if (c.isFavorite()) characters.add(c);
			}
		} else {
			characters.addAll(charactersCopy);
		}

		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
		ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.list_row_character, parent, false);

		return new CharacterViewHolder(binding, listener);
	}

	@Override
	public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
		final Character character = characters.get(position);
		holder.bind(character);
	}

	@Override
	public int getItemCount() {
		return characters.size();
	}

	protected static class CharacterViewHolder extends RecyclerView.ViewHolder {

		private final ViewDataBinding binding;
		private final CharactersListener charactersListener;

		CharacterViewHolder(ViewDataBinding binding, CharactersListener charactersListener) {
			super(binding.getRoot());
			this.binding = binding;
			this.charactersListener = charactersListener;
		}

		void bind(Character character) {
			binding.setVariable(BR.character, character);
			binding.getRoot().findViewById(R.id.tbtn_list_row_character_favorite).setOnClickListener(v -> charactersListener.onFavoriteClick((ToggleButton) v, character));
			binding.getRoot().setOnClickListener(v -> charactersListener.onItemClick(character));
			binding.executePendingBindings();
		}
	}

	@BindingAdapter("loadImage")
	public static void loadImage(ImageView view, String url) {
		Picasso.with(view.getContext()).load(url).into(view);
	}

}