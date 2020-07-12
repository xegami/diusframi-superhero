package com.xegami.diusframi.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Biography implements Serializable {

	private String fullName;
	private String alterEgos;
	private String[] aliases;
	private String placeOfBirth;
	private String firstAppearance;
	private String publisher;
	private String alignment;

}
