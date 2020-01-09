package lokinsky.dope.plugin;
import lokinsky.dope.plugin.DataModels.Clients;
import lokinsky.dope.plugin.Models.Client;
import lokinsky.dope.plugin.Observer.Observer;
import java.lang.Thread;
import java.sql.SQLException;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class DopeListener implements Listener {
	private Dope dope;
	private Client client;
	private Clients clients;
	private	DopeEventObserver dopeEventObserver;
	private int count;
	
	public Client get_client() {
		return this.client;
	}
	public Dope get_dope() {
		return this.dope;
	}
	
	public void set_client(Client client) {
		this.client = client;
	}
	public void set_event_observer(DopeEventObserver dopeEventObserver) {
		this.dopeEventObserver = dopeEventObserver;
	}
	
	
	public DopeListener(Dope plugin) {
		this.count = 0;
		this.dope = plugin;
		this.clients = plugin.getClients();
		//this.client = dope.get_client();
		dope.getServer().getPluginManager().registerEvents(this, dope);

        
    }
	public Clients getClients() {
		return clients;
	}
	public void setClients(Clients clients) {
		this.clients = clients;
	}
	@EventHandler
	public void onLogin(PlayerLoginEvent event) throws SQLException {
		
		//this.set_client(new Client(event.getPlayer().getName(),event.getPlayer().getUniqueId()));
		//dope.getServer().getLogger().info(clients.get(event.getPlayer().getUniqueId()).get_uuid()+"");
		dope.get_pdo_mysql().sql.get("get_user").setString(1, event.getPlayer().getName());
		dope.get_pdo_mysql().sql.get("get_user").execute();
		String res = "";
		if(dope.get_pdo_mysql().sql.get("get_user").getResultSet().next()) {
			res = dope.get_pdo_mysql().sql.get("get_user").getResultSet().getString(2);
			//dope.getServer().getLogger().info(res);
			clients.add(new Client(event.getPlayer().getName(),event.getPlayer().getUniqueId()));
			this.dopeEventObserver.setClients(clients);
		}else {
			event.disallow(Result.KICK_OTHER, "You must register before play –– minecraft.fools.dope");
		}
	
		
		
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {

	}
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event) {
		//count+=1;
		//this.get_client().setCountCommandWrite(count);
		//this.dope.getServer().getLogger().info("ONCOMMAND: "+this.get_client().getCountCommandWrite());
		//this.dopeEventObserver.set_client(this.get_client());
	}
	@EventHandler
	public void onMovement(PlayerMoveEvent event) {
//!this.get_client().isAuth()
		//dope.getServer().getLogger().info(clients.get(event.getPlayer().getUniqueId()).isAuth()+"");
		if(!clients.get(event.getPlayer().getUniqueId()).isAuth()) {
			event.getPlayer().teleport(event.getFrom());
			event.getPlayer().sendMessage("Login first with command /dlogin <password>.");
		}
		
	}
	@EventHandler
	public void onDisconnect(PlayerQuitEvent event) {
		clients.remove(event.getPlayer().getUniqueId());
		this.dopeEventObserver.setClients(clients);
		//dope.getServer().getLogger().info(clients.get(event.getPlayer().getUniqueId()).isAuth()+" ");
		
	}
	
	
}

