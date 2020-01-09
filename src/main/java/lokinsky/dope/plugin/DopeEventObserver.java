package lokinsky.dope.plugin;

import lokinsky.dope.plugin.DataModels.Clients;
import lokinsky.dope.plugin.Models.Client;
import lokinsky.dope.plugin.Observer.Observer;

public class DopeEventObserver implements Observer {

		private DopeListener listener;
		private Dope dope;
		
			
		public Dope get_dope() {
			return this.dope;
		}
		
		
		public DopeEventObserver(DopeListener listener,Dope dope) {
			this.dope = dope;
			this.listener = listener;
			
		}
		
		@Override
		public void update(Clients clients) {
			this.listener.setClients(clients);
		}
		
		public void setClients(Clients clients) {
			this.get_dope().get_client_data().setClients(clients);
			//this.get_dope().get_client_data().notifyObservers();
		}
		
		public void registerListener() {
			this.listener.set_event_observer(this);
		}


}
