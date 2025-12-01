package be.nolwen.lumina.listeners.player;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import be.nolwen.lumina.Main;
import be.nolwen.lumina.utilities.enumeration.Query;

public class AsyncPlayerPreLogin implements Listener {
	
	@EventHandler
	public void onAsyncPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
		Main.getInstance().getSQLManager().executeQuery(Query.RETRIEVE_PLAYER, (raw) -> {
			Object isConnected = false;
			
			if(!raw.isEmpty()) {
				Map<String, Object> result = raw.get(0);
				
				if(result.get("LAST_SEEN") != null && result.get("IP") != null) {
					LocalDateTime lastSeen = (LocalDateTime) result.get("LAST_SEEN");
					LocalDateTime now = LocalDateTime.now();
					
					isConnected = (!result.get("IP").equals(event.getAddress().getHostAddress()) || Duration.between(lastSeen, now).toDays() >= 7 ? false : null);
				}
			}
			
			Main.getInstance().getSQLManager().executeQuery(
					Query.UPDATE_PLAYER, event.getUniqueId().toString(), event.getName(),
					event.getAddress().getHostAddress(), null, isConnected
			);
		}, event.getUniqueId().toString(), event.getName(), event.getAddress().getHostAddress());
	}

}
