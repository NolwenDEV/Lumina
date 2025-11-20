package be.nolwen.lumina.utilities.builder;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {
	
	private ItemStack itemStack;
	
		// ---------------------------------------- \\
	
	public ItemBuilder(Material material) {
		if(material == null) material = Material.AIR;
		this.itemStack = new ItemStack(material);
	}
	
	public ItemBuilder(Material material, int amount) {
		if(material == null) material = Material.AIR;
		this.itemStack = new ItemStack(material, amount);
	}
	
		// ---------------------------------------- \\
	
	public ItemBuilder setAmount(int amount) {
		itemStack.setAmount(amount);
		return this;
	}
	
	public ItemBuilder setDisplayName(String displayName) {
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.setDisplayName(displayName);
		itemStack.setItemMeta(itemMeta);
		return this;
	}
	
	public ItemBuilder setLore(List<String> lore) {
		ItemMeta itemMeta = itemStack.getItemMeta();
				
		itemMeta.setLore(lore);
		itemStack.setItemMeta(itemMeta);
		return this;
	}
	
		// ---------------------------------------- \\
	
	public ItemStack build() { return itemStack; }

}
