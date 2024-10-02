package dev.pschmalz.clean_architecture_demo.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClientRoom implements Runnable {

	private Socket clientSocket;
	private InputStream in;
	private OutputStream out;
	private Queue<String> messages;
	private AtomicBoolean inUse = new AtomicBoolean(true);
	
	public ClientRoom(Socket socket) throws IOException {
		this.clientSocket = socket;
		
		this.in = socket.getInputStream();
		this.out = socket.getOutputStream();
		
	}
	
	@Override
	public void run() {
		while(inUse.get()) {
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				throw new IllegalStateException("Sleep failed!");
			}
		}
	}
	
	public AtomicBoolean getUsed() {
		return inUse;
	}
}
