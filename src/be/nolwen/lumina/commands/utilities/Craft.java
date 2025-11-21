package be.nolwen.lumina.commands.utilities;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.nolwen.lumina.Main;
import be.nolwen.lumina.utilities.Utils;
import be.nolwen.lumina.utilities.annotation.registrar.CommandRegistrar;

@CommandRegistrar(NAME = "craft")
public class Craft implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
		if(!command.getName().equalsIgnoreCase("craft")) return true;
		if(!Main.getInstance().getConfigManager().isCraftModule()) return Utils.error(sender, Main.getInstance().getLanguageManager().getFeatureDisabledError());
		if(!(sender instanceof Player)) return Utils.error(sender, Main.getInstance().getLanguageManager().getPlayerOnlyCommandError());
		
		Player player = (Player) sender;
		player.openWorkbench(null, true);
		
		return true;
	}

}
