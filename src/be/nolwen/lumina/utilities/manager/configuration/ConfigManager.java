package be.nolwen.lumina.utilities.manager.configuration;

import java.util.HashMap;
import java.util.Map;

import be.nolwen.lumina.utilities.annotation.configuration.Configuration;

public class ConfigManager {
	
	@Configuration(FILE = "Configuration", KEY = "PREFIX")
	private String prefix;
	
	@Configuration(FILE = "Configuration", KEY = "VERSION")
	private String version;
	
				// -------------------- \\
	
	@Configuration(FILE = "Configuration", KEY = "MODULE.PREMIUM")
	private boolean premiumModule;
	
	@Configuration(FILE = "Configuration", KEY = "MODULE.DATABASE")
	private boolean databaseModule;
	
	@Configuration(FILE = "Configuration", KEY = "MODULE.CHAT")
	private boolean chatModule;
	
	@Configuration(FILE = "Configuration", KEY = "MODULE.CREEPER_GRIEFING")
	private boolean creeperGriefingModule;
	
	@Configuration(FILE = "Configuration", KEY = "MODULE.CROP_REPLANTER")
	private boolean cropReplanterModule;
	
	@Configuration(FILE = "Configuration", KEY = "MODULE.COMMAND.PRIVATE_MESSAGE")
	private boolean privateMessageModule;
	
	@Configuration(FILE = "Configuration", KEY = "MODULE.COMMAND.BACKPACK")
	private boolean backpackModule;
	
	@Configuration(FILE = "Configuration", KEY = "MODULE.COMMAND.FILTER")
	private boolean filterModule;
	
	@Configuration(FILE = "Configuration", KEY = "MODULE.COMMAND.TRASH")
	private boolean trashModule;
	
	@Configuration(FILE = "Configuration", KEY = "MODULE.COMMAND.HOME")
	private boolean homeModule;
	
	@Configuration(FILE = "Configuration", KEY = "MODULE.COMMAND.CRAFT")
	private boolean craftModule;
	
	@Configuration(FILE = "Configuration", KEY = "MODULE.COMMAND.BACK")
	private boolean backModule;
	
				// -------------------- \\
		
	@Configuration(FILE = "Configuration", KEY = "CHAT.FORMAT")
	private String chatFormat;
	
	@Configuration(FILE = "Configuration", KEY = "CHAT.PREFIXES")
	private Map<String, String> chatPrefixes = new HashMap<>();
	
		// ---------------------------------------- \\
	
	public String getPrefix() { return prefix; }
	public String getVersion() { return version; }
	
				// -------------------- \\
	
	public boolean isPremiumModule() { return premiumModule; }
	public boolean isDatabaseModule() { return databaseModule; }
	public boolean isChatModule() { return chatModule; }
	public boolean isCreeperGriefingModule() { return creeperGriefingModule; }
	public boolean isCropReplanterModule() { return cropReplanterModule; }
	
	public boolean isPrivateMessageModule() { return privateMessageModule; }
	public boolean isBackpackModule() { return backpackModule; }
	public boolean isFilterModule() { return filterModule; }
	public boolean isTrashModule() { return trashModule; }
	public boolean isHomeModule() { return homeModule; }
	public boolean isCraftModule() { return craftModule; }
	public boolean isBackModule() { return backModule; }
	
				// -------------------- \\
	
	public String getChatFormat() { return chatFormat; }
	public Map<String, String> getChatPrefixes() { return chatPrefixes; }

}
