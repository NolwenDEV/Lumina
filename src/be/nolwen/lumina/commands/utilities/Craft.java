package be.nolwen.lumina.commands.utilities;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.nolwen.lumina.Main;
import be.nolwen.lumina.utilities.annotation.registrar.CommandRegistrar;
import be.nolwen.lumina.utilities.builder.MessageBuilder;

@CommandRegistrar(NAME = "craft")
public class Craft implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
		if(!command.getName().equalsIgnoreCase("craft")) return true;
		if(!Main.getInstance().getConfigManager().isCraftModule()) return MessageBuilder.error(sender, Main.getInstance().getLanguageManager().getFeatureDisabledError());
		if(!(sender instanceof Player)) return MessageBuilder.error(sender, Main.getInstance().getLanguageManager().getPlayerOnlyCommandError());
		
		Player player = (Player) sender;
		player.openWorkbench(null, true);
		
		return true;
	}

}
