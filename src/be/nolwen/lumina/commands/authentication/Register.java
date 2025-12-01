package be.nolwen.lumina.commands.authentication;

import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mindrot.jbcrypt.BCrypt;

import be.nolwen.lumina.Main;
import be.nolwen.lumina.utilities.Utils;
import be.nolwen.lumina.utilities.annotation.registrar.CommandRegistrar;
import be.nolwen.lumina.utilities.enumeration.Query;

@CommandRegistrar(NAME = "register")
public class Register implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
		if(!command.getName().equals("register")) return true;
		if(!Main.getInstance().getConfigManager().isAuthenticationModule()) return Utils.error(sender, Main.getInstance().getLanguageManager().getFeatureDisabledError());
		if(!Main.getInstance().getDatabaseManager().isEnabled()) return Utils.error(sender, Main.getInstance().getLanguageManager().getDatabaseDisabledError());
		if(!(sender instanceof Player)) return Utils.error(sender, Main.getInstance().getLanguageManager().getPlayerOnlyCommandError());
		if(!Main.getInstance().getDataManager().getRestrictedPlayers().containsKey((Player) sender))
			return Utils.error(sender, Main.getInstance().getLanguageManager().getAuthenticationAlreadyLoggedIn());
		if(arguments.length < 2) return Utils.error(sender, Main.getInstance().getLanguageManager().getRegisterUsageError());
		if(arguments[0].length() > 24) return Utils.error(sender, Main.getInstance().getLanguageManager().getAuthenticationPasswordTooLong());
		if(!arguments[1].equalsIgnoreCase(Main.getInstance().getDataManager().getCaptcha().get((Player) sender)))
			return Utils.error(sender, Main.getInstance().getLanguageManager().getAuthenticationWrongCaptchaError());
		
		Player player = (Player) sender;
		
		Main.getInstance().getSQLManager().executeQuery(Query.RETRIEVE_PLAYER, (raw) -> {
			if(!raw.isEmpty()) {
				Map<String, Object> result = raw.get(0);
				
				if(result.get("PASSWORD") == null) {
					Main.getInstance().getSQLManager().executeQuery(
							Query.UPDATE_PLAYER, player.getUniqueId().toString(), player.getName(), player.getAddress().getAddress().getHostAddress(),
							BCrypt.hashpw(arguments[0], BCrypt.gensalt()), true
					);
					
					player.sendMessage(Utils.format(Main.getInstance().getLanguageManager().getAuthenticationSuccessfullyRegisteredMessage()));
					
					Main.getInstance().getDataManager().getRestrictedPlayers().remove(player);
					Main.getInstance().getDataManager().getCaptcha().remove(player);
				} else {
					player.sendMessage(Utils.format(Main.getInstance().getLanguageManager().getAuthenticationAlreadyRegisteredError()));
				}
			}
		}, player.getUniqueId().toString(), player.getName(), player.getAddress().getAddress().getHostAddress());
		
		return true;
	}

}
