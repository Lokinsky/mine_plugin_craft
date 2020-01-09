package lokinsky.dope.plugin;

import lokinsky.dope.plugin.DataModels.Clients;
import lokinsky.dope.plugin.Observer.Observer;

public class DopeEventObserver implements Observer {

		private DopeListener listener;
		private Clients clients;
		private Dope dope;
		
		
		
		public DopeEventObserver(DopeListener listener,Dope dope) {
			this.dope = dope;
			this.listener = listener;
			this.clients = new Clients();
			dope.getServer().getPluginManager().registerEvents(listener, dope);
			
		}
		
		@Override
		public void update(Clients clients) {
			this.clients = clients;
		}
		

		public void refreshClients() {
			dope.get_client_data().setClients(clients);
		}
		public Clients getClients() {
			return this.clients;
		}
		public void registerListener() {
			this.listener.set_event_observer(this);
		}
		public void Logger(String log) {
			dope.getServer().getLogger().info(log);
		}


}
