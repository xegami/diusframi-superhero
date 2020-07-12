package com.xegami.diusframi.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Appearance implements Serializable {
	private String gender;
	private String race;
	private String[] height;
	private String[] weight;
	private String eyeColor;
	private String hairColor;
}
