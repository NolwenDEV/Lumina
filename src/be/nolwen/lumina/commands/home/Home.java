package be.nolwen.lumina.commands.home;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.nolwen.lumina.Main;
import be.nolwen.lumina.utilities.annotation.registrar.CommandRegistrar;
import be.nolwen.lumina.utilities.builder.MessageBuilder;
import be.nolwen.lumina.utilities.enumeration.Query;

@CommandRegistrar(NAME = "home")
public class Home implements CommandExecutor {
	
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
		if(!command.getName().equalsIgnoreCase("home")) return true;
		if(!Main.getInstance().getConfigManager().isHomeModule()) return MessageBuilder.error(sender, Main.getInstance().getLanguageManager().getFeatureDisabledError());
		if(!Main.getInstance().getConfigManager().isDatabaseModule()) return MessageBuilder.error(sender, Main.getInstance().getLanguageManager().getDatabaseDisabledError());
		if(!(sender instanceof Player)) return MessageBuilder.error(sender, Main.getInstance().getLanguageManager().getPlayerOnlyCommandError());
		if(arguments.length < 1) return MessageBuilder.error(sender, Main.getInstance().getLanguageManager().getHomeUsageError());
		
		Player player = (Player) sender;
		
		Main.getInstance().getSQLManager().executeQuery(Query.RETRIEVE_HOME, (result) -> {
			List<Map<String, Object>> homeData = new ArrayList<>();
			if(!result.isEmpty()) {
				for(Map<String, Object> home : result) {
					if(home.get("NAME").toString().equalsIgnoreCase(arguments[0])) {
						homeData.add(home);
					}
				}
			}
				
			if(!homeData.isEmpty()) {
				Map<String, Object> home = homeData.get(0);
					
				Bukkit.getScheduler().runTask(Main.getInstance(), () -> {
					player.teleport(new Location(
							Bukkit.getWorld((String) home.get("WORLD")),
							(double) home.get("X"), (double) home.get("Y"), (double) home.get("Z"),
							(float) home.get("YAW"), (float) home.get("PITCH")
					));
				});
					
				player.sendMessage(MessageBuilder.format(
						Main.getInstance().getLanguageManager().getHomeTeleportedMessage(),
						Map.of("HOME", arguments[0].toLowerCase())
				));
			} else {
				player.sendMessage(MessageBuilder.format(Main.getInstance().getLanguageManager().getHomeNotFoundError()));
			}
		}, player.getUniqueId().toString(), player.getName());
			
		return true;
    }

}
