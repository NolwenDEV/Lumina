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

@CommandRegistrar(NAME = "login")
public class Login implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
		if(!command.getName().equals("login")) return true;
		if(!Main.getInstance().getConfigManager().isAuthenticationModule()) return Utils.error(sender, Main.getInstance().getLanguageManager().getFeatureDisabledError());
		if(!Main.getInstance().getDatabaseManager().isEnabled()) return Utils.error(sender, Main.getInstance().getLanguageManager().getDatabaseDisabledError());
		if(!(sender instanceof Player)) return Utils.error(sender, Main.getInstance().getLanguageManager().getPlayerOnlyCommandError());
		if(!Main.getInstance().getDataManager().getRestrictedPlayers().containsKey((Player) sender))
			return Utils.error(sender, Main.getInstance().getLanguageManager().getAuthenticationAlreadyLoggedIn());
		if(arguments.length < 2) return Utils.error(sender, Main.getInstance().getLanguageManager().getLoginUsageError());
		if(!arguments[1].equalsIgnoreCase(Main.getInstance().getDataManager().getCaptcha().get((Player) sender)))
			return Utils.error(sender, Main.getInstance().getLanguageManager().getAuthenticationWrongCaptchaError());
		
		Player player = (Player) sender;
		
		Main.getInstance().getSQLManager().executeQuery(Query.RETRIEVE_PLAYER, (raw) -> {
			if(!raw.isEmpty()) {
				Map<String, Object> result = raw.get(0);
				
				if(result.get("PASSWORD") != null) {
					if(BCrypt.checkpw(arguments[0], (String) result.get("PASSWORD"))) {
						Main.getInstance().getSQLManager().executeQuery(
								Query.UPDATE_PLAYER, player.getUniqueId().toString(), player.getName(), player.getAddress().getAddress().getHostAddress(),
								null, true
						);
						
						player.sendMessage(Utils.format(Main.getInstance().getLanguageManager().getAuthenticationSuccessfullyLoggedInMessage()));
						
						Main.getInstance().getDataManager().getRestrictedPlayers().remove(player);
						Main.getInstance().getDataManager().getCaptcha().remove(player);
					} else {
						player.sendMessage(Utils.format(Main.getInstance().getLanguageManager().getAuthenticationWrongPasswordError()));
					}
				} else {
					player.sendMessage(Main.getInstance().getLanguageManager().getAuthenticationNotRegisteredError());
				}
			}
		}, player.getUniqueId().toString(), player.getName(), player.getAddress().getAddress().getHostAddress());
		
		return true;
	}

}
