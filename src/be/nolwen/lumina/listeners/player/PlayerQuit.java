package be.nolwen.lumina.listeners.player;

import java.util.Map;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import be.nolwen.lumina.Main;
import be.nolwen.lumina.utilities.builder.MessageBuilder;

public class PlayerQuit implements Listener {

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		event.setQuitMessage(MessageBuilder.format(
				Main.getInstance().getLanguageManager().getEventQuit(),
				Map.of("PLAYER", event.getPlayer().getName())
		));
		
		Main.getInstance().getDataManager().getMessagedPlayers().remove(event.getPlayer());
	}
	
}