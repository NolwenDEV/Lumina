package be.nolwen.lumina.commands.menu;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.nolwen.lumina.Main;
import be.nolwen.lumina.utilities.Utils;
import be.nolwen.lumina.utilities.annotation.registrar.CommandRegistrar;
import be.nolwen.lumina.utilities.builder.InventoryBuilder;
import be.nolwen.lumina.utilities.builder.ItemBuilder;

@CommandRegistrar(NAME = "filter")
public class Filter implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
		if(!command.getName().equalsIgnoreCase("filter")) return true;
		if(!Main.getInstance().getConfigManager().isFilterModule()) return Utils.error(sender, Main.getInstance().getLanguageManager().getFeatureDisabledError());
		if(!(sender instanceof Player)) return Utils.error(sender, Main.getInstance().getLanguageManager().getPlayerOnlyCommandError());
		
		Player player = (Player) sender;	
		InventoryBuilder inventory = new InventoryBuilder(4, Main.getInstance().getLanguageManager().getGUIFilterTitle());
				
		if (Main.getInstance().getDataManager().getFilteredItems().get(player) != null && !Main.getInstance().getDataManager().getFilteredItems().get(player).isEmpty()) {
			int slot = 0;
			for (Material m : Main.getInstance().getDataManager().getFilteredItems().get(player)) {
				if (slot > 17) break;
				inventory.setItem(slot, new ItemBuilder(m, 1).build());
				slot++;
			}
		}
				
		inventory.setItem(new int[] {18, 19, 20, 21, 22, 23, 24, 25, 26},
				new ItemBuilder(Material.getMaterial(Main.getInstance().getLanguageManager().getGUIFilterSeparatorButton().get("MATERIAL")), 1)
					.setDisplayName(Main.getInstance().getLanguageManager().getGUIFilterSeparatorButton().get("NAME"))
					.build()
		);
		inventory.setItem(29, (!Main.getInstance().getDataManager().getFilterActive().containsKey(player)
				? new ItemBuilder(Material.getMaterial(Main.getInstance().getLanguageManager().getGUIFilterToggleEnableButton().get("MATERIAL")), 1)
						.setDisplayName(Main.getInstance().getLanguageManager().getGUIFilterToggleEnableButton().get("NAME"))
						.setLore(Utils.buildLore(Main.getInstance().getLanguageManager().getGUIFilterToggleEnableButton().get("LORE")))
						.build()
				: new ItemBuilder(Material.getMaterial(Main.getInstance().getLanguageManager().getGUIFilterToggleDisableButton().get("MATERIAL")), 1)
						.setDisplayName(Main.getInstance().getLanguageManager().getGUIFilterToggleDisableButton().get("NAME"))
						.setLore(Utils.buildLore(Main.getInstance().getLanguageManager().getGUIFilterToggleDisableButton().get("LORE")))
						.build()
		));
		inventory.setItem(31, (!Main.getInstance().getDataManager().getFilterDestroyerActive().containsKey(player)
				? new ItemBuilder(Material.getMaterial(Main.getInstance().getLanguageManager().getGUIFilterDestroyerEnableButton().get("MATERIAL")), 1)
						.setDisplayName(Main.getInstance().getLanguageManager().getGUIFilterDestroyerEnableButton().get("NAME"))
						.setLore(Utils.buildLore(Main.getInstance().getLanguageManager().getGUIFilterDestroyerEnableButton().get("LORE")))
						.build()
				: new ItemBuilder(Material.getMaterial(Main.getInstance().getLanguageManager().getGUIFilterDestroyerDisableButton().get("MATERIAL")), 1)
						.setDisplayName(Main.getInstance().getLanguageManager().getGUIFilterDestroyerDisableButton().get("NAME"))
						.setLore(Utils.buildLore(Main.getInstance().getLanguageManager().getGUIFilterDestroyerDisableButton().get("LORE")))
						.build()
		));
		inventory.setItem(33, new ItemBuilder(Material.getMaterial(Main.getInstance().getLanguageManager().getGUIFilterClearButton().get("MATERIAL")), 1)
				.setDisplayName(Main.getInstance().getLanguageManager().getGUIFilterClearButton().get("NAME"))
				.setLore(Utils.buildLore(Main.getInstance().getLanguageManager().getGUIFilterClearButton().get("LORE")))
				.build()
		);
				
		player.openInventory(inventory.build());
		
		return true;
	}

}
