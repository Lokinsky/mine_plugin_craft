package lokinsky.dope.plugin.DataModels;

import java.util.HashMap;
import java.util.UUID;

import lokinsky.dope.plugin.Command.Command;
import lokinsky.dope.plugin.Models.Client;

public class Clients {
	private HashMap<UUID, Client> clients;
	public Clients(int length) {
		this.clients = new HashMap<>(length); 
	}
	public boolean add(Client client) {
		if(!clients.containsKey(client.get_uuid())) {
			clients.put(client.get_uuid(), client);
			return true;
		}else {
			return false;
		}
	}
	public boolean remove(UUID uuid) {
		if(clients.containsKey(uuid)) {
			clients.remove(uuid);
			return true;
		}else {
			return false;
		}
	}
	public Client get(UUID uuid) {
		if(clients.containsKey(uuid)) {
				return clients.get(uuid);
		}else {
			return null;
		}
	}
}
