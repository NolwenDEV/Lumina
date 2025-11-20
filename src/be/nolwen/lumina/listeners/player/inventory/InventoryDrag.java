package be.nolwen.lumina.listeners.player.inventory;

import java.util.Arrays;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;

import be.nolwen.lumina.Main;

public class InventoryDrag implements Listener {
	
	@EventHandler
	public void onInventoryDrag(InventoryDragEvent event) {
		if(!Main.getInstance().getConfigManager().isFilterModule()) return;
		if(!Arrays.asList(Main.getInstance().getDataManager().getInventories()).contains(event.getView().getTitle())) return;
		
		if(event.getView().getTitle().equalsIgnoreCase(Main.getInstance().getLanguageManager().getGUIFilterTitle())) {
			if(event.getRawSlots().stream().anyMatch(slot -> slot < event.getView().getTopInventory().getSize())) {
				event.setCancelled(true);
			}
		}
	}

}
