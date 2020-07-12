package com.xegami.diusframi.rest.event;

import org.greenrobot.eventbus.EventBus;

import lombok.Data;

@Data
public abstract class BaseEvent {

	private String error = "";

	public void post() {
		EventBus.getDefault().post(this);
	}
}
