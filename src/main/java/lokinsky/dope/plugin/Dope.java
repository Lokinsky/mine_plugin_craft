package lokinsky.dope.plugin;

import java.util.Collection;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import lokinsky.dope.plugin.Observer.Observer;
import lokinsky.dope.plugin.Command.Switch;
import lokinsky.dope.plugin.DataModels.Clients;
import lokinsky.dope.plugin.Logic.PDO_MYSQL;
import lokinsky.dope.plugin.Models.Client;
import lokinsky.dope.plugin.Observer.ClientData;

public final class Dope extends JavaPlugin implements Observer{

	private ClientData clientData;
	private Bootstrap bootstrap;
	private Switch command;
	private Client _client;
	private Clients clients;
	private PDO_MYSQL pdo_mysql;
	
	public ClientData get_client_data() {
		return this.clientData;
	}
	public Clients getClients() {
		return clients;
	}
	public void setClients(Clients clients) {
		this.clients = clients;
	}
	public PDO_MYSQL get_pdo_mysql() {
		return pdo_mysql;
	}
	public Dope() {
		bootstrap = new Bootstrap(this);
		this.clientData = new ClientData();
		command = bootstrap.get_commands();
		clients = new Clients(150);
		pdo_mysql = bootstrap.get_pdo_mysql();
		
		
	}
	
	
	
	
	
	public void onEnable() {
        
		DopeEventObserver dopeEventObserver = new DopeEventObserver(new DopeListener(this),this);
		dopeEventObserver.registerListener();
		clientData.registerObserver(this);
		clientData.registerObserver(dopeEventObserver);

    }
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		//getLogger().info("Commands wrote: "+ this.get_client().getCountCommandWrite());
		//!this.get_client().isAuth()&&command.getName().equalsIgnoreCase("dlogin")
		if(!this.clients.get(this.getServer()
				.getPlayer(sender.getName())
				.getUniqueId())
				.isAuth()
				&& command.getName().equalsIgnoreCase("dlogin")) {
			
			boolean state = this.command.execute(command.getName(),sender, args);
			
			
			return state;
		}
		if(this.clients.get(sender.getServer()
				.getPlayer(sender.getName())
				.getUniqueId())
				.isAuth()) {
			return true;
		}
		
		return false;
		
	}
	@Override
	public void update(Clients clients) {
		this.setClients(clients);
	}

}
