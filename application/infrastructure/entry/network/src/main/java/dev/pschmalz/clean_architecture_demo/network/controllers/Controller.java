package dev.pschmalz.clean_architecture_demo.network.controllers;

import dev.pschmalz.clean_architecture_demo.network.data.Message;

public interface Controller {
	public void processMessage(Message message);
}
