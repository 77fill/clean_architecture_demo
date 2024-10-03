package dev.pschmalz.clean_architecture_demo.network;

import dev.pschmalz.clean_architecture_demo.network.data.Message;
import jakarta.json.bind.JsonbBuilder;

public class Listener implements Runnable {
	private ClientRoom parent;
	
	public Listener(ClientRoom parent) {
		this.parent = parent;
	}
	
	@Override
	public void run() {
		var jsonb = JsonbBuilder.create();
		var lobby = parent.getParent();
		var incomingMessages = lobby.getIncomingMessages();
		
		while(parent.getInUse().get()) {
			
			var message = jsonb.fromJson(parent.getIn(), Message.class);
			incomingMessages.add(message);
			
		}
	}
}
