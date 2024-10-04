package dev.pschmalz.clean_architecture_demo.network;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dev.pschmalz.clean_architecture_demo.network.data.Message;
import jakarta.json.bind.JsonbBuilder;

public class Listener implements Runnable {
	private ClientRoom parent;
	private Logger logger = LogManager.getRootLogger();
	
	public Listener(ClientRoom parent) {
		this.parent = parent;
	}
	
	@Override
	public void run() {
		var jsonb = JsonbBuilder.create();
		var lobby = parent.getParent();
		var incomingMessages = lobby.getIncomingMessages();
		
		
		while(parent.getRunning().get()) {
			try {
				logger.warn("THIS IS A TEST");
				var jsonString = parent.getIn().readLine();
				var message = jsonb.fromJson(jsonString, Message.class);
				incomingMessages.add(message);
				
			} catch(IOException e) {
				throw new IllegalStateException(e);
			} 
		}
	}
}
