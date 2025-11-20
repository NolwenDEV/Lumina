package be.nolwen.lumina.commands.utilities;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.nolwen.lumina.Main;
import be.nolwen.lumina.utilities.annotation.registrar.CommandRegistrar;
import be.nolwen.lumina.utilities.builder.InventoryBuilder;
import be.nolwen.lumina.utilities.builder.ItemBuilder;
import be.nolwen.lumina.utilities.builder.MessageBuilder;

@CommandRegistrar(NAME = "filter")
public class Filter implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
		if(!command.getName().equalsIgnoreCase("filter")) return true;
		if(!Main.getInstance().getConfigManager().isFilterModule()) return MessageBuilder.error(sender, Main.getInstance().getLanguageManager().getFeatureDisabledError());
		if(!(sender instanceof Player)) return MessageBuilder.error(sender, Main.getInstance().getLanguageManager().getPlayerOnlyCommandError());
		
		Player player = (Player) sender;	
		InventoryBuilder inventory = new InventoryBuilder(4, Main.getInstance().getLanguageManager().getGUIFilterTitle());
				
		if (Main.getInstance().getDataManager().getFilteredItems().get(player) != null
				&& !Main.getInstance().getDataManager().getFilteredItems().get(player).isEmpty()) {
			int slot = 0;
			for (Material m : Main.getInstance().getDataManager().getFilteredItems().get(player)) {
				if (slot > 17) break;
					inventory.setItem(slot, new ItemBuilder(m, 1).build());
					slot++;
			}
		}
				
		inventory.setItem(new int[] {18, 19, 20, 21, 22, 23, 24, 25, 26}, new ItemBuilder(Material.PINK_STAINED_GLASS_PANE, 1).setDisplayName(" ").build());
		inventory.setItem(29, (!Main.getInstance().getDataManager().getFilterActive().containsKey(player)
				? new ItemBuilder(Material.GREEN_WOOL, 1).setDisplayName("§aEnable").build()
				: new ItemBuilder(Material.RED_WOOL, 1).setDisplayName("§cDisable").build()));
		inventory.setItem(33, new ItemBuilder(Material.CAULDRON, 1)
				.setDisplayName(Main.getInstance().getLanguageManager().getGUIFilterClearButton())
				.build());
				
		player.openInventory(inventory.build());
		
		return true;
	}

}
