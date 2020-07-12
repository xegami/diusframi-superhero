package com.xegami.diusframi.rest.controller;

import com.xegami.diusframi.rest.event.BaseEvent;

public abstract class BaseController {

	protected <T extends BaseEvent> void onFailureEvent(T event) {
		event.setError("Sin respuesta del servidor.");
		event.post();
	}
}
