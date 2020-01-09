package lokinsky.dope.plugin;
import lokinsky.dope.plugin.Logic.PDO_MYSQL;
import lokinsky.dope.plugin.Models.Client;
import java.sql.SQLException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class DopeListener implements Listener {
	
	private DopeEventObserver dopeEventObserver;
	private PDO_MYSQL pdo_mysql;
	
	public void set_event_observer(DopeEventObserver dopeEventObserver) {
		this.dopeEventObserver = dopeEventObserver;
		
	}
	
	
	public DopeListener() {
        
    }
	public DopeListener(PDO_MYSQL pdo_mysql) {
		this.pdo_mysql = pdo_mysql;
	}
	@EventHandler
	public void onLogin(PlayerLoginEvent event) throws SQLException {

		pdo_mysql.sql.get("get_user").setString(1, event.getPlayer().getName());
		pdo_mysql.sql.get("get_user").execute();
		if(pdo_mysql.sql.get("get_user").getResultSet().next()) {

			dopeEventObserver.getClients().add(new Client(event.getPlayer().getName(),event.getPlayer().getUniqueId()));
			dopeEventObserver.refreshClients();

		}else {
			event.disallow(Result.KICK_OTHER, "You must register before play –– minecraft.fools.dope");
		}
	
		
		
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {

	}
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event) {
		 
	}
	@EventHandler
	public void onMovement(PlayerMoveEvent event) {
 
		if(!dopeEventObserver.getClients().get(event.getPlayer().getName()).isAuth()) {
			event.getPlayer().teleport(event.getFrom());
			event.getPlayer().sendMessage("Login first with command /dlogin <password>.");
			dopeEventObserver.Logger("PlayerMoveEvent@"+event.getPlayer().getName());
		}
		
	}
	@EventHandler
	public void onDisconnect(PlayerQuitEvent event) {
		dopeEventObserver.getClients().remove(event.getPlayer().getName());
		dopeEventObserver.refreshClients();
		 
		
	}
	
	
}

