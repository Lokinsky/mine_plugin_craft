package lokinsky.dope.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import lokinsky.dope.plugin.Observer.Observer;
import lokinsky.dope.plugin.Command.Switch;
import lokinsky.dope.plugin.DataModels.Clients;
import lokinsky.dope.plugin.Logic.PDO_MYSQL;
import lokinsky.dope.plugin.Observer.ClientData;

public final class Dope extends JavaPlugin implements Observer{

	private ClientData clientData;
	private Bootstrap bootstrap;
	private Switch command;
	private Clients clients;
	private PDO_MYSQL pdo_mysql;
	
	public ClientData get_client_data() {
		return this.clientData;
	}
	public Dope() {
		bootstrap = new Bootstrap();
		clients = new Clients();
		clientData = bootstrap.getClientData();
		command = bootstrap.get_commands();
		pdo_mysql = bootstrap.get_pdo_mysql();
	}
	
	
	
	
	
	public void onEnable() {
        
		DopeEventObserver dopeEventObserver = new DopeEventObserver(new DopeListener(this.pdo_mysql),this);
		dopeEventObserver.registerListener();
		
		clientData.registerObserver(this);
		clientData.registerObserver(dopeEventObserver);

    }
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) throws NullPointerException{

		
		if(!this.clients.get(sender.getName())
				.isAuth()
				&& command.getName().equalsIgnoreCase("dlogin")) {
			
			boolean state = this.command.execute(command.getName(),sender, args);
			
			
			return state;
		}
		if(this.clients.get(sender.getName())
				.isAuth()) {
			return true;
		}
		
		return false;
		
	}
	@Override
	public void update(Clients clients) {
		
		this.clients = clients;
	}

}
