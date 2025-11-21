package be.nolwen.lumina.commands.home;

import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.nolwen.lumina.Main;
import be.nolwen.lumina.utilities.Utils;
import be.nolwen.lumina.utilities.annotation.registrar.CommandRegistrar;
import be.nolwen.lumina.utilities.enumeration.Query;

@CommandRegistrar(NAME = "delhome")
public class HomeRemove implements CommandExecutor {
	
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
		if(!command.getName().equalsIgnoreCase("delhome")) return true;
		if(!Main.getInstance().getConfigManager().isHomeModule()) return Utils.error(sender, Main.getInstance().getLanguageManager().getFeatureDisabledError());
		if(!Main.getInstance().getDatabaseManager().isEnabled()) return Utils.error(sender, Main.getInstance().getLanguageManager().getDatabaseDisabledError());
		if(!(sender instanceof Player)) return Utils.error(sender, Main.getInstance().getLanguageManager().getPlayerOnlyCommandError());
		if(arguments.length < 1) return Utils.error(sender, Main.getInstance().getLanguageManager().getDelHomeUsageError());
		
		Player player = (Player) sender;
		
		Main.getInstance().getSQLManager().executeQuery(Query.REMOVE_HOME, (result) -> {
			if(!result.isEmpty()) {
				player.sendMessage(Utils.format(
						Main.getInstance().getLanguageManager().getHomeRemovedMessage(),
						Map.of("HOME", arguments[0])
				));
			} else {
				player.sendMessage(Utils.format(Main.getInstance().getLanguageManager().getHomeNotFoundError()));
			}
		}, player.getUniqueId().toString(), player.getName(), arguments[0].toLowerCase());
		
		return true;
    }

}
