package be.nolwen.lumina.commands.menu;

import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import be.nolwen.lumina.Main;
import be.nolwen.lumina.utilities.ItemSerializer;
import be.nolwen.lumina.utilities.annotation.registrar.CommandRegistrar;
import be.nolwen.lumina.utilities.builder.InventoryBuilder;
import be.nolwen.lumina.utilities.builder.MessageBuilder;
import be.nolwen.lumina.utilities.enumeration.Query;

@CommandRegistrar(NAME = "backpack")
public class Backpack implements CommandExecutor {
	
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
		if(!command.getName().equalsIgnoreCase("backpack")) return true;
		if(!Main.getInstance().getConfigManager().isBackpackModule()) return MessageBuilder.error(sender, Main.getInstance().getLanguageManager().getFeatureDisabledError());
		if(!Main.getInstance().getDatabaseManager().isEnabled()) return MessageBuilder.error(sender, Main.getInstance().getLanguageManager().getDatabaseDisabledError());
		if(!(sender instanceof Player)) return MessageBuilder.error(sender, Main.getInstance().getLanguageManager().getPlayerOnlyCommandError());
		
		Player player = (Player) sender;
		InventoryBuilder inventory = new InventoryBuilder(3, Main.getInstance().getLanguageManager().getGUIBackpackTitle());
		
		Main.getInstance().getSQLManager().executeQuery(Query.RETRIEVE_BACKPACK, (result) -> {
			if(!result.isEmpty()) {
				Map<String, Object> values = result.get(0);
				
				ItemStack[] itemStacks = null;
				itemStacks = ItemSerializer.deserializeList((String) values.get("ITEM"));
				
				if(itemStacks != null) {
					inventory.setContent(ItemSerializer.deserializeList((String) values.get("ITEM")));
				}
			}
		}, player.getUniqueId().toString(), player.getName());
		
		player.openInventory(inventory.build());
		return true;
    }
	
}
