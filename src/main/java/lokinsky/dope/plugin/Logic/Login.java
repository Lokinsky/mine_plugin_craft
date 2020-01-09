package lokinsky.dope.plugin.Logic;

import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.command.CommandSender;

import lokinsky.dope.plugin.Dope;
import lokinsky.dope.plugin.Command.Command;
import lokinsky.dope.plugin.Observer.ClientData;

public class Login implements Command{
	private ClientData clientData;
	private PDO_MYSQL pdo_mysql;
	public Login(ClientData clientData, PDO_MYSQL pdo_mysql) {
		this.clientData = clientData;
		this.pdo_mysql = pdo_mysql;
	}
	private boolean _login(String args[],CommandSender sender) throws SQLException {
		sender.sendMessage(pdo_mysql+" 1");
		sender.sendMessage(clientData+"2");
		pdo_mysql.sql.get("get_user").setString(1, sender.getName());
		pdo_mysql.sql.get("get_user").execute();
		String res = "";
		if(pdo_mysql.sql.get("get_user").getResultSet().next()) {
			res = pdo_mysql.sql.get("get_user").getResultSet().getString(4);
		}else {
			return false;
		}
		
		
		//sender.sendMessage("Logged in with "+args[0]+" player: "+sender.getName());
		if(res.equalsIgnoreCase(args[0])) {
			sender.sendMessage("Logged in with "+res);
			clientData.getClients().get(sender.getName()).setAuth(true);
			clientData.notifyObservers();
			//dope.get_client_data().setClients(dope.getClients());
			return true;
		}
		else {
			sender.sendMessage("Wrong password for: "+sender.getName());
			return false;
		}
		
		
		//dope.get_client_data().notifyObservers();
		
	}
	@Override
	public boolean execute() {
		return false;
	}
	@Override
	public boolean execute(String args[],CommandSender sender) {
		boolean status;
		try {
			status = _login(args,sender);
			sender.sendMessage(status+"");
			return status;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
}
