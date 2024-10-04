package dev.pschmalz.clean_architecture_demo.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

import dev.pschmalz.clean_architecture_demo.network.data.Message;
import jakarta.json.bind.JsonbBuilder;

public class ClientRoom implements Runnable {

	private Lobby parent;
	private Socket clientSocket;
	private BufferedReader in;
	private OutputStreamWriter out;
	private Queue<Message> outputMessages = new ConcurrentLinkedQueue<>();
	private AtomicBoolean running = new AtomicBoolean(true);
	private Executor executor;
	
	public ClientRoom(Lobby parent, Executor executor, Socket socket) throws IOException {
		this.clientSocket = socket;
		this.parent = parent;
		this.executor = executor;
		
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.out = new OutputStreamWriter(socket.getOutputStream());
		
	}
	
	@Override
	public void run() {
		executor.execute(new Listener(this));
		executor.execute(new Speaker(this));
	}
	
	public AtomicBoolean getRunning() {
		return running;
	}

	public BufferedReader getIn() {
		return in;
	}

	public OutputStreamWriter getOut() {
		return out;
	}

	public Queue<Message> getOutputMessages() {
		return outputMessages;
	}
	
	public Lobby getParent() {
		return this.parent;
	}
}
