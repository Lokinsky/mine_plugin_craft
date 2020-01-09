package lokinsky.dope.plugin.DataModels;

import java.util.HashMap;
import java.util.UUID;

import lokinsky.dope.plugin.Command.Command;
import lokinsky.dope.plugin.Models.Client;

public class Clients {
	private HashMap<String, Client> clients;
	public Clients() {
		this.clients = new HashMap<>(); 
	}
	public boolean add(Client client) {
		if(!clients.containsKey(client.getName())) {
			clients.put(client.getName(), client);
			return true;
		}else {
			return false;
		}
	}
	public boolean remove(String name) {
		if(clients.containsKey(name)) {
			clients.remove(name);
			return true;
		}else {
			return false;
		}
	}
	public Client get(String name) {
		if(clients.containsKey(name)) {
				return clients.get(name);
		}else {
			return null;
		}
	}
}
