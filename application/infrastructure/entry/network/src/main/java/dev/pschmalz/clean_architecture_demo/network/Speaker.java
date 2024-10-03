package dev.pschmalz.clean_architecture_demo.network;

import java.io.IOException;

import jakarta.json.bind.JsonbBuilder;

public class Speaker implements Runnable {
	private ClientRoom parent;
	
	public Speaker(ClientRoom parent) {
		this.parent = parent;
	}
	
	@Override
	public void run() {
		var jsonb = JsonbBuilder.create();
		
		while(parent.getInUse().get()) {
					
			if(!parent.getOutputMessages().isEmpty())
				while(!parent.getOutputMessages().isEmpty())
					try {
						parent.getOut().write(jsonb.toJson(parent.getOutputMessages().poll()));
					} catch (IOException e) {
						break;
					}
	
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				throw new IllegalStateException("Sleep failed!");
			}
		}
	}
}
