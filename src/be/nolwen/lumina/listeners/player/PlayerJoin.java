package be.nolwen.lumina.listeners.player;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import be.nolwen.lumina.Main;
import be.nolwen.lumina.utilities.builder.MessageBuilder;
import be.nolwen.lumina.utilities.enumeration.Query;

public class PlayerJoin implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		event.setJoinMessage(MessageBuilder.format(
				Main.getInstance().getLanguageManager().getEventJoin(),
				Map.of("PLAYER", event.getPlayer().getName())
		));
				
		Main.getInstance().getSQLManager().executeQuery(Query.UPDATE_PLAYER, event.getPlayer().getUniqueId().toString(), event.getPlayer().getName());	
		Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
	        event.getPlayer().setHealth(event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
	    }, 5L);
	}
	
}