package dev.pschmalz.clean_architecture_demo.network.controllers;

import dev.pschmalz.clean_architecture_demo.network.data.Message;
import jakarta.json.bind.JsonbBuilder;

public class PlayerController implements Controller {
	@Override
	public void processMessage(Message message) {
		var jsonb = JsonbBuilder.create();
		
		System.out.println(jsonb.toJson(message));
	}
}
