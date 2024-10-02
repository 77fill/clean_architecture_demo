package dev.pschmalz.clean_architecture_demo;

import java.util.concurrent.Executors;

import dev.pschmalz.clean_architecture_demo.network.Lobby;

public class Application {
    public static void main(String[] args) {
    	var executor = Executors.newFixedThreadPool(20);
    	
        try(var lobby = new Lobby(8080, executor)) {
        	System.out.println("This worked!!");
        }
    }
}
