package be.nolwen.lumina.commands.teleportation;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.nolwen.lumina.Main;
import be.nolwen.lumina.utilities.Utils;
import be.nolwen.lumina.utilities.annotation.registrar.CommandRegistrar;

@CommandRegistrar(NAME = "back")
public class Back implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
		if(!command.getName().equalsIgnoreCase("back")) return true;
		if(!Main.getInstance().getConfigManager().isBackModule()) return Utils.error(sender, Main.getInstance().getLanguageManager().getFeatureDisabledError());
		if(!(sender instanceof Player)) return Utils.error(sender, Main.getInstance().getLanguageManager().getPlayerOnlyCommandError());
		
		Player player = (Player) sender;
		if(!Main.getInstance().getDataManager().getDeadPlayers().containsKey(player))
			return Utils.error(sender, Main.getInstance().getLanguageManager().getBackNotDeadError());
		
		player.teleport(Main.getInstance().getDataManager().getDeadPlayers().get(player));
		Main.getInstance().getDataManager().getDeadPlayers().remove(player);
		
		return true;
	}

}
