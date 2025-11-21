package be.nolwen.lumina.listeners.player.inventory;

import java.util.Map;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import be.nolwen.lumina.Main;
import be.nolwen.lumina.utilities.ItemSerializer;
import be.nolwen.lumina.utilities.Utils;
import be.nolwen.lumina.utilities.enumeration.Query;

public class InventoryClose implements Listener {
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		if(event.getPlayer() == null) return;
		
		if(event.getView().getTitle().equalsIgnoreCase(Main.getInstance().getLanguageManager().getGUITrashTitle())) { trashManager(event); }
		else if(event.getView().getTitle().equalsIgnoreCase(Main.getInstance().getLanguageManager().getGUIBackpackTitle())) { backpackManager(event); }
	}
	
		// ---------------------------------------- \\
	
	private void trashManager(InventoryCloseEvent event) {
		if(!Main.getInstance().getConfigManager().isTrashModule()) return;
		
		int totalItems = 0;
		for(ItemStack item : event.getInventory().getContents()) {
			if(item != null) {
				totalItems += item.getAmount();
			}
		}
		
		if(totalItems > 0) {
			event.getPlayer().sendMessage(Utils.format(
					Main.getInstance().getLanguageManager().getTrashCounterMessage(),
					Map.of("COUNT", String.valueOf(totalItems))
			));
			event.getInventory().clear();
		}
	}
	
	private void backpackManager(InventoryCloseEvent event) {
		if(!Main.getInstance().getConfigManager().isTrashModule()) return;
		
		Main.getInstance().getSQLManager().executeQuery(Query.UPDATE_BACKPACK, ItemSerializer.serializeList(event.getInventory().getContents()),
				event.getPlayer().getUniqueId().toString(),
				event.getPlayer().getName()
		);
	}

}
