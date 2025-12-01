package be.nolwen.lumina.listeners.player;

import java.security.SecureRandom;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import be.nolwen.lumina.Main;
import be.nolwen.lumina.utilities.Utils;
import be.nolwen.lumina.utilities.enumeration.Query;

public class PlayerJoin implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		event.setJoinMessage(Utils.format(
				Main.getInstance().getLanguageManager().getEventJoin(),
				Map.of("PLAYER", event.getPlayer().getName())
		));
				
		if (Main.getInstance().getConfigManager().isAuthenticationModule()) {
            Main.getInstance().getSQLManager().executeQuery(Query.RETRIEVE_PLAYER, raw -> {
            	if(!raw.isEmpty()) {
            		Map<String, Object> result = raw.get(0);
            		
            		if(result.get("CONNECTED") != null && !(Boolean) result.get("CONNECTED")) {
            			Main.getInstance().getDataManager().getRestrictedPlayers().put(event.getPlayer(), true);
            			Main.getInstance().getDataManager().getCaptcha().put(event.getPlayer(), String.format("%06d", new SecureRandom().nextInt(1_000_000)));
            			
            			if(result.get("PASSWORD") != null) {
            				event.getPlayer().sendMessage(Utils.format(
            						Main.getInstance().getLanguageManager().getAuthenticationLoginMessage(),
            						Map.of("CAPTCHA", Main.getInstance().getDataManager().getCaptcha().get(event.getPlayer()))
            				));
            			} else {
            				event.getPlayer().sendMessage(Utils.format(
            						Main.getInstance().getLanguageManager().getAuthenticationRegisterMessage(),
            						Map.of("CAPTCHA", Main.getInstance().getDataManager().getCaptcha().get(event.getPlayer()))
            				));
            			}
            		}
            	}
            }, event.getPlayer().getUniqueId().toString(), event.getPlayer().getName(), event.getPlayer().getAddress().getAddress().getHostAddress());
        }
		
		Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
	        event.getPlayer().setHealth(event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
	    }, 5L);
	}
	
}