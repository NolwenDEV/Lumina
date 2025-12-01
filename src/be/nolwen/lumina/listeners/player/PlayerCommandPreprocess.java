package be.nolwen.lumina.listeners.player;

import java.util.Set;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import be.nolwen.lumina.Main;

public class PlayerCommandPreprocess implements Listener {
	
	private final Set<String> allowedCommands = Set.of("/login", "/register");
	
		// ---------------------------------------- \\
	
	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
		if(!Main.getInstance().getConfigManager().isAuthenticationModule()) return;
		if(!Main.getInstance().getDataManager().getRestrictedPlayers().containsKey(event.getPlayer())) return;
		if(allowedCommands.contains(event.getMessage().split(" ")[0].toLowerCase())) return;
		
		event.setCancelled(true);
	}

}
