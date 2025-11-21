package be.nolwen.lumina.listeners.entity;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

import be.nolwen.lumina.Main;

public class EntityPickupItem implements Listener {
	
	@EventHandler
	public void onPickup(EntityPickupItemEvent event) {
		if(event.getEntity() == null) return;
		if(!(event.getEntity() instanceof Player)) return;
		if(!Main.getInstance().getConfigManager().isFilterModule()) return;
		
		Player player = (Player) event.getEntity();
		
		if(!Main.getInstance().getDataManager().getFilterActive().containsKey(player)) return;
		if(Main.getInstance().getDataManager().getFilteredItems().get(player) == null) return;
		if(Main.getInstance().getDataManager().getFilteredItems().get(player).isEmpty()) return;
		
		for (Material material : Main.getInstance().getDataManager().getFilteredItems().get(player)) {
	        if(event.getItem().getItemStack().getType().equals(material)) {
	        	event.setCancelled(true);
	        	if(Main.getInstance().getDataManager().getFilterDestroyerActive().containsKey(player)) {
	        		if(Main.getInstance().getDataManager().getFilterDestroyerActive().get(player)) { event.getItem().remove(); }
	        	}
	        }
	    }
	}

}
