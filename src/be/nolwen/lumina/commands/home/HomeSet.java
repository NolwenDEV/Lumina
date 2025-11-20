package be.nolwen.lumina.commands.home;

import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.nolwen.lumina.Main;
import be.nolwen.lumina.utilities.annotation.registrar.CommandRegistrar;
import be.nolwen.lumina.utilities.builder.MessageBuilder;
import be.nolwen.lumina.utilities.enumeration.Query;

@CommandRegistrar(NAME = "sethome")
public class HomeSet implements CommandExecutor {
	
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
		if(!command.getName().equalsIgnoreCase("sethome")) return true;
		if(!Main.getInstance().getConfigManager().isHomeModule()) return MessageBuilder.error(sender, Main.getInstance().getLanguageManager().getFeatureDisabledError());
		if(!Main.getInstance().getConfigManager().isDatabaseModule()) return MessageBuilder.error(sender, Main.getInstance().getLanguageManager().getDatabaseDisabledError());
		if(!(sender instanceof Player)) return MessageBuilder.error(sender, Main.getInstance().getLanguageManager().getPlayerOnlyCommandError());
		if(arguments.length < 1) return MessageBuilder.error(sender, Main.getInstance().getLanguageManager().getSetHomeUsageError());
		
		Player player = (Player) sender;
		
		Main.getInstance().getSQLManager().executeQuery(Query.UPDATE_HOME,
				arguments[0].toLowerCase(), player.getWorld().getName(),
				player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(),
				player.getLocation().getYaw(), player.getLocation().getPitch(),
				player.getUniqueId().toString(), player.getName()
		);
		
		player.sendMessage(MessageBuilder.format(
				Main.getInstance().getLanguageManager().getHomeAddedMessage(),
				Map.of("HOME", arguments[0])
		));
		
		return true;
    }

}
