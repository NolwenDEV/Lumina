package be.nolwen.lumina.utilities.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import be.nolwen.lumina.Main;

public class DataManager {
	
	private String[] inventories = {
			Main.getInstance().getLanguageManager().getGUITrashTitle(),
			Main.getInstance().getLanguageManager().getGUIBackpackTitle(),
			Main.getInstance().getLanguageManager().getGUIFilterTitle()
	};
	
	private Map<Player, Player> messagedPlayers;
	private Map<Player, Location> deadPlayers;
	private Map<Player, Player> teleportRequests;
	private Map<Player, Set<Material>> filteredItems;
	private Map<Player, Boolean> filterActive;
	private Map<Player, Boolean> filterDestroyerActive;
	
	private boolean updateFound;
	
		// ---------------------------------------- \\
	
	public DataManager() {
		this.messagedPlayers = new HashMap<>();
		this.deadPlayers = new HashMap<>();
		this.teleportRequests = new HashMap<>();
		this.filteredItems = new HashMap<>();
		this.filterActive = new HashMap<>();
		this.filterDestroyerActive = new HashMap<>();
		
		this.updateFound = false;
	}
	
		// ---------------------------------------- \\
	
	public String[] getInventories() { return inventories; }
	
	public Map<Player, Player> getMessagedPlayers() { return messagedPlayers; }
	public Map<Player, Location> getDeadPlayers() { return deadPlayers; }
	public Map<Player, Player> getTeleportRequests() { return teleportRequests; }
	public Map<Player, Set<Material>> getFilteredItems() { return filteredItems; }
	public Map<Player, Boolean> getFilterActive() { return filterActive; }
	public Map<Player, Boolean> getFilterDestroyerActive() { return filterDestroyerActive; }
	
	public boolean isUpdateFound() { return updateFound; }
	public void setUpdateFound(boolean updateFound) { this.updateFound = updateFound; }

}
