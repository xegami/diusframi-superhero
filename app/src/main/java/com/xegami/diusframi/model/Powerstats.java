package com.xegami.diusframi.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Powerstats implements Serializable {
	private String intelligence;
	private String strength;
	private String speed;
	private String durability;
	private String power;
	private String combat;
}
