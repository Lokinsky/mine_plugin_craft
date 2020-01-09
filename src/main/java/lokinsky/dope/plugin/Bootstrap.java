package lokinsky.dope.plugin;

import lokinsky.dope.plugin.Command.Switch;
import lokinsky.dope.plugin.Logic.Login;
import lokinsky.dope.plugin.Logic.PDO_MYSQL;

public class Bootstrap {
	private Switch _switch;
	private PDO_MYSQL pdo_mysql;
	public Bootstrap(Dope dope) {
		_switch = new Switch();
		pdo_mysql = new PDO_MYSQL();
		INITIALIZE_COMMANDS(dope);
	}
	private void INITIALIZE_COMMANDS(Dope dope) {
		_switch.register("dlogin", new Login(dope));
	}
	public Switch get_commands(){
		return _switch;
	}
	public PDO_MYSQL get_pdo_mysql() {
		return pdo_mysql;
	}
	
}
