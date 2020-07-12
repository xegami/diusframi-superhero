package com.xegami.diusframi.rest.event;

import com.xegami.diusframi.model.Character;

import java.util.List;

import lombok.Data;

@Data
public class CharactersEvent extends BaseEvent {

	private List<Character> characters;

}
