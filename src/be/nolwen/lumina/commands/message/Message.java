package be.nolwen.lumina.commands.message;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.nolwen.lumina.Main;
import be.nolwen.lumina.utilities.Utils;
import be.nolwen.lumina.utilities.annotation.registrar.CommandRegistrar;

@CommandRegistrar(NAME = "message")
public class Message implements CommandExecutor {
	
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
		if(!command.getName().equalsIgnoreCase("message")) return true;
		if(!Main.getInstance().getConfigManager().isPrivateMessageModule()) return Utils.error(sender, Main.getInstance().getLanguageManager().getFeatureDisabledError());
		if(!(sender instanceof Player)) return Utils.error(sender, Main.getInstance().getLanguageManager().getPlayerOnlyCommandError());
		if(arguments.length < 2) return Utils.error(sender, Main.getInstance().getLanguageManager().getMessageUsageError());
		
		Player player = (Player) sender;
		Player target = Bukkit.getPlayerExact(arguments[0]);
		
		if(target == player) return Utils.error(sender, Main.getInstance().getLanguageManager().getMessageCannotMessageYourselfError());
		if(!target.isOnline()) return Utils.error(sender, Main.getInstance().getLanguageManager().getOfflinePlayerError());
		
		StringBuilder stringBuilder = new StringBuilder();
		for(int index = 1; index < arguments.length; index++) {
			stringBuilder.append(String.format("%s%s", arguments[index], (index == arguments.length ? "" : " ")));
		}
		
		player.sendMessage(Utils.format(Main.getInstance().getConfigManager().getChatPrivateSentFormat(),
				Map.of("PLAYER", target.getName(), "MESSAGE", stringBuilder.toString())
		));
		target.sendMessage(Utils.format(Main.getInstance().getConfigManager().getChatPrivateReceivedFormat(),
				Map.of("PLAYER", player.getName(), "MESSAGE", stringBuilder.toString())
		));
		
		Main.getInstance().getDataManager().getMessagedPlayers().put(player, target);
		Main.getInstance().getDataManager().getMessagedPlayers().put(target, player);
		
		return true;
    }

}
