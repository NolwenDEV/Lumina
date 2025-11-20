package be.nolwen.lumina.utilities.builder;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryBuilder {
	
	private Inventory inventory;
	
		// ---------------------------------------- \\
	
	public InventoryBuilder(int rows, String title) {
		this.inventory = Bukkit.createInventory(null, (rows * 9), title);
	}
	
		// ---------------------------------------- \\
	
	public InventoryBuilder setItem(int[] slots, ItemStack itemStack) {
		for(int slot : slots) {
			inventory.setItem(slot, itemStack);
		}
		return this;
	}
	
	public InventoryBuilder setItem(int slot, ItemStack itemStack) {
		inventory.setItem(slot, itemStack);
		return this;
	}
	
	public InventoryBuilder setContent(ItemStack[] itemStacks) {
		inventory.setContents(itemStacks);
		return this;
	}
	
		// ---------------------------------------- \\
	
	public Inventory build() { return inventory; }

}
