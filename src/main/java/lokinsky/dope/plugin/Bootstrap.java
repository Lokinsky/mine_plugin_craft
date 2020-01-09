package lokinsky.dope.plugin;

import lokinsky.dope.plugin.Command.Switch;
import lokinsky.dope.plugin.Logic.Login;
import lokinsky.dope.plugin.Logic.PDO_MYSQL;
import lokinsky.dope.plugin.Observer.ClientData;

public class Bootstrap {
	private Switch _switch;
	private PDO_MYSQL pdo_mysql;
	private ClientData clientData;
	public Bootstrap() {
		_switch = new Switch();
		pdo_mysql = new PDO_MYSQL();
		this.clientData = new ClientData();
		INITIALIZE_COMMANDS(clientData,pdo_mysql);
	}
	private void INITIALIZE_COMMANDS(ClientData clientData, PDO_MYSQL pdo_mysql) {
		_switch.register("dlogin", new Login(clientData,pdo_mysql));
	}
	public Switch get_commands(){
		return _switch;
	}
	public PDO_MYSQL get_pdo_mysql() {
		return pdo_mysql;
	}
	public ClientData getClientData() {
		return clientData;
	}
	
}
