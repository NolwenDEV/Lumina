package be.nolwen.lumina.listeners.block;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;

import be.nolwen.lumina.Main;

public class BlockDropItem implements Listener {
	
	@EventHandler
	public void onBlockDrop(BlockDropItemEvent event) {
		Block block = event.getBlock();
		BlockData blockData = block.getBlockData();
		
		if(!(blockData instanceof Ageable age)) return;
		if(age.getAge() < age.getMaximumAge()) return;
		
		Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
			if(block.getType() == Material.AIR) {
				Ageable newAge = (Ageable) age.clone();
				newAge.setAge(0);
				block.setBlockData(newAge);
			}
		}, 1L);
	}

}
