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
import be.nolwen.lumina.utilities.Utils;
import be.nolwen.lumina.utilities.builder.ItemBuilder;

public class InventoryClick implements Listener {
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if(event.getWhoClicked() == null) return;
		if(event.getClickedInventory() == null) return;
		if(!Main.getInstance().getConfigManager().isFilterModule()) return;
		if(!Arrays.asList(Main.getInstance().getDataManager().getInventories()).contains(event.getView().getTitle())) return;
		if(!event.getView().getTitle().equalsIgnoreCase(Main.getInstance().getLanguageManager().getGUIFilterTitle())) return;
		if(!event.getClickedInventory().equals(event.getView().getTopInventory())) return;
		
		Player player = (Player) event.getWhoClicked();
		
		if(event.getSlot() == 29) {
			if(!Main.getInstance().getDataManager().getFilterActive().containsKey(player)) {
				event.getInventory().setItem(event.getSlot(),
						new ItemBuilder(Material.getMaterial(Main.getInstance().getLanguageManager().getGUIFilterToggleDisableButton().get("MATERIAL")))
							.setDisplayName(Main.getInstance().getLanguageManager().getGUIFilterToggleDisableButton().get("NAME"))
							.setLore(Utils.buildLore(Main.getInstance().getLanguageManager().getGUIFilterToggleDisableButton().get("LORE")))
							.build()
				);
				
				Main.getInstance().getDataManager().getFilterActive().put(player, true);
				player.sendMessage(Utils.format(Main.getInstance().getLanguageManager().getFilterToggleEnabledMessage()));
			} else {
				event.getInventory().setItem(event.getSlot(),
						new ItemBuilder(Material.getMaterial(Main.getInstance().getLanguageManager().getGUIFilterToggleEnableButton().get("MATERIAL")))
							.setDisplayName(Main.getInstance().getLanguageManager().getGUIFilterToggleEnableButton().get("NAME"))
							.setLore(Utils.buildLore(Main.getInstance().getLanguageManager().getGUIFilterToggleEnableButton().get("LORE")))
							.build()
				);
				
				Main.getInstance().getDataManager().getFilterActive().remove(player, true);
				player.sendMessage(Utils.format(Main.getInstance().getLanguageManager().getFilterToggleDisabledMessage()));
			}
		} else if(event.getSlot() == 31) {
			if(!Main.getInstance().getDataManager().getFilterDestroyerActive().containsKey(player)) {
				event.getInventory().setItem(event.getSlot(),
						new ItemBuilder(Material.getMaterial(Main.getInstance().getLanguageManager().getGUIFilterDestroyerDisableButton().get("MATERIAL")))
							.setDisplayName(Main.getInstance().getLanguageManager().getGUIFilterDestroyerDisableButton().get("NAME"))
							.setLore(Utils.buildLore(Main.getInstance().getLanguageManager().getGUIFilterDestroyerDisableButton().get("LORE")))
							.build()
				);
				
				Main.getInstance().getDataManager().getFilterDestroyerActive().put(player, true);
				player.sendMessage(Utils.format(Main.getInstance().getLanguageManager().getFilterDestroyerEnabledMessage()));
			} else {
				event.getInventory().setItem(event.getSlot(),
						new ItemBuilder(Material.getMaterial(Main.getInstance().getLanguageManager().getGUIFilterDestroyerEnableButton().get("MATERIAL")))
							.setDisplayName(Main.getInstance().getLanguageManager().getGUIFilterDestroyerEnableButton().get("NAME"))
							.setLore(Utils.buildLore(Main.getInstance().getLanguageManager().getGUIFilterDestroyerEnableButton().get("LORE")))
							.build()
				);
				
				Main.getInstance().getDataManager().getFilterDestroyerActive().remove(player, true);
				player.sendMessage(Utils.format(Main.getInstance().getLanguageManager().getFilterDestroyerDisabledMessage()));
			}
		} else if(event.getSlot() == 33) {
			for(int slot = 0; slot <= 17; slot++) { event.getInventory().setItem(slot, new ItemBuilder(Material.AIR).build()); }
			Main.getInstance().getDataManager().getFilteredItems().remove(player);
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
						player.sendMessage(Utils.format(Main.getInstance().getLanguageManager().getFilterNoDuplicateItemError()));
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
		if(event.getClick().isShiftClick() || event.getClick() == ClickType.DOUBLE_CLICK) event.setCancelled(true);
	}

}
