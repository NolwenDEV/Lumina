package be.nolwen.lumina.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import be.nolwen.lumina.Main;

public class PlayerMove implements Listener {
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		if(!Main.getInstance().getConfigManager().isAuthenticationModule()) return;
		
		if(Main.getInstance().getDataManager().getRestrictedPlayers().containsKey(event.getPlayer())) event.setCancelled(true);
	}

}
