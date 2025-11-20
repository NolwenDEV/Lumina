package be.nolwen.lumina.listeners.entity;

import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import be.nolwen.lumina.Main;

public class EntityExplode implements Listener {
	
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent event) {
		if(!Main.getInstance().getConfigManager().isCreeperGriefingModule()) return;
		if(event.getEntity() instanceof Creeper) { event.blockList().clear(); }
	}

}
