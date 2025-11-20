package be.nolwen.lumina.commands.utilities;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.nolwen.lumina.Main;
import be.nolwen.lumina.utilities.annotation.registrar.CommandRegistrar;
import be.nolwen.lumina.utilities.builder.InventoryBuilder;
import be.nolwen.lumina.utilities.builder.MessageBuilder;

@CommandRegistrar(NAME = "trash")
public class Trash implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
		if(!command.getName().equalsIgnoreCase("trash")) return true;
		if(!Main.getInstance().getConfigManager().isTrashModule()) return MessageBuilder.error(sender, Main.getInstance().getLanguageManager().getFeatureDisabledError());
		if(!(sender instanceof Player)) return MessageBuilder.error(sender, Main.getInstance().getLanguageManager().getPlayerOnlyCommandError());
		
		Player player = (Player) sender;
		player.openInventory(new InventoryBuilder(6, Main.getInstance().getLanguageManager().getGUITrashTitle()).build());
		
		return true;
	}

}
