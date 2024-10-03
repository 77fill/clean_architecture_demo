package dev.pschmalz.clean_architecture_demo.network.data;

import java.io.Serializable;

public class Message implements Serializable {
	private static final long serialVersionUID = 8046532332533883312L;
	
	private String source;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
}
