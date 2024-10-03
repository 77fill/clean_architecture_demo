package dev.pschmalz.clean_architecture_demo.network;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

import dev.pschmalz.clean_architecture_demo.network.data.Message;

public class Lobby implements Closeable {
	private ServerSocket serverSocket;
	private AtomicBoolean open = new AtomicBoolean(true);
	private ExecutorService executor;
	private Queue<Message> incomingMessages = new ConcurrentLinkedQueue<>();
	

	/**
	 * TODO: exit when (open == false) even if waiting for 'accept'
	 */
	public void awaitClients() {
		while(open.get()) {
			try {
				var socket = serverSocket.accept();
				
				executor.execute(new ClientRoom(this, executor, socket));
			} catch (IOException e) {
				throw new IllegalStateException("Server socket could not 'accept' connections.");
			}
		}
	}
	
	public Lobby(int port, ExecutorService executor) {
		this.executor = executor;
		
		validate(port);
		
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			throw new IllegalStateException("Could not access port " + port);
		}
	}
	
	private void validate(int port) {
		if(port <= 0)
			throw new IllegalArgumentException("Port needs to be > 0");
	}
	
	@Override
	public void close() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			throw new IllegalStateException("Could not close serverSocket on port" + serverSocket.getLocalPort());
		}
	}
	
	public AtomicBoolean getOpen() {
		return open;
	}

	public Queue<Message> getIncomingMessages() {
		return incomingMessages;
	}

	public ExecutorService getExecutor() {
		return executor;
	}
	
	
}
