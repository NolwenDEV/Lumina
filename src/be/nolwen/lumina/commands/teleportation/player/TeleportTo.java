package be.nolwen.lumina.commands.teleportation.player;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.nolwen.lumina.Main;
import be.nolwen.lumina.utilities.Utils;
import be.nolwen.lumina.utilities.annotation.registrar.CommandRegistrar;

@CommandRegistrar(NAME = "teleportto")
public class TeleportTo implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
		if(!command.getName().equalsIgnoreCase("teleportto")) return true;
		if(!Main.getInstance().getConfigManager().isTeleportationModule()) return Utils.error(sender, Main.getInstance().getLanguageManager().getFeatureDisabledError());
		if(!(sender instanceof Player)) return Utils.error(sender, Main.getInstance().getLanguageManager().getPlayerOnlyCommandError());
		if(arguments.length < 1) return Utils.error(sender, Main.getInstance().getLanguageManager().getTeleportToUsageError());
		
		Player player = (Player) sender;
		Player target = Bukkit.getPlayerExact(arguments[0]);
		
		if(target == player) return Utils.error(sender, Main.getInstance().getLanguageManager().getTeleportationCannotTeleportYourselfError());
		if(!target.isOnline()) return Utils.error(sender, Main.getInstance().getLanguageManager().getOfflinePlayerError());
		
		if(!Main.getInstance().getDataManager().getTeleportRequests().containsKey(target)
				&& !Main.getInstance().getDataManager().getTeleportRequests().containsKey(player)) {
			player.sendMessage(Utils.format(Main.getInstance().getLanguageManager().getTeleportationRequestSentMessage(), Map.of("PLAYER", target.getName())));
			target.sendMessage(Utils.format(Main.getInstance().getLanguageManager().getTeleportationRequestReceivedMessage(),
					Map.of("PLAYER", player.getName(), "COMMAND", "tphere")
			));
			
			Main.getInstance().getDataManager().getTeleportRequests().put(player, target);
			Main.getInstance().getDataManager().getTeleportRequests().put(target, player);
		} else {
			target.teleport(player.getLocation());
			
			player.sendMessage(Utils.format(Main.getInstance().getLanguageManager().getTeleportedAtMessage(), Map.of("PLAYER", target.getName())));
			target.sendMessage(Utils.format(Main.getInstance().getLanguageManager().getTeleportedToMessage(), Map.of("PLAYER", player.getName())));
			
			Main.getInstance().getDataManager().getTeleportRequests().remove(player);
			Main.getInstance().getDataManager().getTeleportRequests().remove(target);
		}
		
		return true;
	}

}
