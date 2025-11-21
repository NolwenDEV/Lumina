package be.nolwen.lumina.listeners.player;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import be.nolwen.lumina.Main;
import be.nolwen.lumina.utilities.Utils;

public class AsyncPlayerChat implements Listener {
	
	@EventHandler
	public void onPlayerMessage(AsyncPlayerChatEvent event) {
		if(event.getPlayer() == null) return;
		if(event.getMessage() == null) return;
		if(!Main.getInstance().getConfigManager().isChatModule()) return;
		
		Bukkit.getConsoleSender().sendMessage(getFormattedMessage(event));
		for(Player player : Bukkit.getOnlinePlayers()) { player.sendMessage(getFormattedMessage(event)); }
		
		event.setCancelled(true);
	}
	
		// ------------------------------ \\
	
	private String getFormattedMessage(AsyncPlayerChatEvent event) {
		return Utils.format(
				Main.getInstance().getConfigManager().getChatFormat(),
				Map.of(
						"CHAT_PREFIX", Main.getInstance().getConfigManager().getChatPrefixes().getOrDefault(
								event.getPlayer().getName(),
								Main.getInstance().getConfigManager().getChatPrefixes().get("DEFAULT")
						),
						"PLAYER", event.getPlayer().getName(),
						"MESSAGE", event.getMessage()
				)
		);
	}

}
