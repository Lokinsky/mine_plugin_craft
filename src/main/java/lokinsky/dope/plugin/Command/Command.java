package lokinsky.dope.plugin.Command;

import org.bukkit.command.CommandSender;

public interface Command {
	boolean execute();
	boolean execute(String[] args,CommandSender sender);
}
