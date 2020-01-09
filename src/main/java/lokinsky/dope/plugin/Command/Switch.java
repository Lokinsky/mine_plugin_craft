package lokinsky.dope.plugin.Command;

import java.util.HashMap;

import org.bukkit.command.CommandSender;


public class Switch {

	public Switch() {
	}
	private final HashMap<String, Command> commandMap = new HashMap<>();
    
    public void register(String commandName, Command command) {
        commandMap.put(commandName, command);
    }
    
    public boolean execute(String commandName) {
        Command command = commandMap.get(commandName);
        if (command == null) {
            throw new IllegalStateException("no command registered for " + commandName);
        }
        return command.execute();
    }
    public boolean execute(String commandName,CommandSender sender,String args[]) {
        Command command = commandMap.get(commandName);
        if (command == null) {
            throw new IllegalStateException("no command registered for " + commandName);
        }
        return command.execute(args,sender);
    }
}
