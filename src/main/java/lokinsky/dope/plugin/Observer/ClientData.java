package lokinsky.dope.plugin.Observer;

import java.util.LinkedList;
import java.util.List;

import lokinsky.dope.plugin.DataModels.Clients;
import lokinsky.dope.plugin.Models.Client;

public class ClientData implements Observable {
	private List<Observer> observers;
	private Clients clients;
	public ClientData() {
	        observers = new LinkedList<>();
	    }
	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
		
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
		
	}

	@Override
	public void notifyObservers() {
		for (Observer observer : observers)
            observer.update(this.clients);
		
	}
	public void setClients(Clients clients) {
	       this.clients = clients;
	       notifyObservers();
	   }

}
