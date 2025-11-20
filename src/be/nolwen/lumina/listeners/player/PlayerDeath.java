package be.nolwen.lumina.listeners.player;

import java.util.Map;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import be.nolwen.lumina.Main;
import be.nolwen.lumina.utilities.builder.MessageBuilder;

public class PlayerDeath implements Listener {
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		event.setDeathMessage(MessageBuilder.format(
				Main.getInstance().getLanguageManager().getEventDeath(),
				Map.of("PLAYER", event.getEntity().getName())
		));
				
		event.getEntity().sendMessage(MessageBuilder.format(Main.getInstance().getLanguageManager().getBackReminderMessage()));
		Main.getInstance().getDataManager().getDeadPlayers().put(event.getEntity(), event.getEntity().getLocation());		
	}

}
