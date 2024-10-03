package dev.pschmalz.clean_architecture_demo.config;

import java.io.Closeable;
import java.util.concurrent.atomic.AtomicBoolean;

import dev.pschmalz.clean_architecture_demo.network.Lobby;
import dev.pschmalz.clean_architecture_demo.network.controllers.Controller;
import dev.pschmalz.clean_architecture_demo.network.controllers.PlayerController;
import dev.pschmalz.clean_architecture_demo.network.data.Message;

public class InternalDispatcher implements Runnable, Closeable {
	private Lobby lobby;
	private AtomicBoolean running = new AtomicBoolean(true);
	
	public InternalDispatcher(Lobby lobby) {
		this.lobby = lobby;
	}
	
	@Override
	public void run() {
		var executor = lobby.getExecutor();
		
		while(running.get()) {
			while(!lobby.getIncomingMessages().isEmpty()) {
				var message = lobby.getIncomingMessages().poll();
				
				switch(message.getSource()) {
				case "player":
					dispatch(new PlayerController(), message);
					break;
				default:
					
				}
			}
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
	
	public void dispatch(Controller controller, Message message) {
		lobby.getExecutor().execute(() -> controller.processMessage(message));
	}
	
	@Override
	public void close() {
		running.setRelease(false);
	}
}
