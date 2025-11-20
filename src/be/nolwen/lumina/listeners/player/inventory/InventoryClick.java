package be.nolwen.lumina.listeners.player.inventory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import be.nolwen.lumina.Main;
import be.nolwen.lumina.utilities.builder.ItemBuilder;
import be.nolwen.lumina.utilities.builder.MessageBuilder;

public class InventoryClick implements Listener {
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if(event.getWhoClicked() == null) return;
		if(event.getClickedInventory() == null) return;
		if(!Main.getInstance().getConfigManager().isFilterModule()) return;
		if(!Arrays.asList(Main.getInstance().getDataManager().getInventories()).contains(event.getView().getTitle())) return;
		
		Player player = (Player) event.getWhoClicked();
		if(event.getView().getTitle().equalsIgnoreCase(Main.getInstance().getLanguageManager().getGUIFilterTitle())) {
			if(event.getClickedInventory().equals(event.getView().getTopInventory())) {
				if(event.getSlot() == 29 || event.getSlot() == 33) {
					if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(
							Main.getInstance().getLanguageManager().getGUIFilterClearButton()
					)) {
						for(int slot = 0; slot <= 17; slot++) { event.getInventory().setItem(slot, new ItemBuilder(Material.AIR).build()); }
						Main.getInstance().getDataManager().getFilteredItems().remove(player);
					} else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(
							Main.getInstance().getLanguageManager().getGUIFilterEnableButton()
					)) {
						if(!Main.getInstance().getDataManager().getFilterActive().containsKey(player)) {
							event.getInventory().setItem(event.getSlot(), new ItemBuilder(Material.RED_WOOL)
									.setDisplayName(Main.getInstance().getLanguageManager().getGUIFilterDisableButton()).build());
							Main.getInstance().getDataManager().getFilterActive().put(player, true);
							
							player.sendMessage(MessageBuilder.format(Main.getInstance().getLanguageManager().getFilterEnabledMessage()));
						}
					} else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(
							Main.getInstance().getLanguageManager().getGUIFilterDisableButton()
					)) {
						if(Main.getInstance().getDataManager().getFilterActive().containsKey(player)) {
							event.getInventory().setItem(event.getSlot(), new ItemBuilder(Material.GREEN_WOOL)
									.setDisplayName(Main.getInstance().getLanguageManager().getGUIFilterEnableButton()).build());
							Main.getInstance().getDataManager().getFilterActive().remove(player);
							
							player.sendMessage(MessageBuilder.format(Main.getInstance().getLanguageManager().getFilterDisabledMessage()));
						}
					}
				}
				
				for(int slot = 0; slot <= 17; slot++) {
					if(event.getSlot() == slot) {
						if(event.getInventory().getItem(slot) == null) {
							boolean alreadyExists = Arrays.stream(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17})
						            .mapToObj(event.getInventory()::getItem)
						            .filter(Objects::nonNull)
						            .anyMatch(itemStack -> itemStack.getType() == event.getCursor().getType());
							
							if(!alreadyExists) {
								event.getInventory().setItem(slot, new ItemBuilder(event.getCursor().getType(), 1).build());
								
								Main.getInstance().getDataManager().getFilteredItems()
								    .computeIfAbsent(player, hashSet -> new HashSet<>())
								    .add(event.getCursor().getType());
							} else {
								player.sendMessage(MessageBuilder.format(Main.getInstance().getLanguageManager().getFilterNoDuplicateItemError()));
							}
						} else {
							Main.getInstance().getDataManager().getFilteredItems().get(player).remove(event.getCurrentItem().getType());
						    if (Main.getInstance().getDataManager().getFilteredItems().get(player).isEmpty()) {
						    	Main.getInstance().getDataManager().getFilteredItems().remove(player);
						    }
							
							event.getInventory().setItem(slot, new ItemBuilder(Material.AIR).build());
						}
					}
				}
				
				event.setCancelled(true);
			}
			
			if(event.getClick().isShiftClick() || event.getClick() == ClickType.DOUBLE_CLICK) event.setCancelled(true);
		}
	}

}
