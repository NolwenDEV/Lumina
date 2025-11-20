package be.nolwen.lumina;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import be.nolwen.lumina.utilities.annotation.configuration.ConfigurationLoader;
import be.nolwen.lumina.utilities.annotation.registrar.Registrar;
import be.nolwen.lumina.utilities.manager.DataManager;
import be.nolwen.lumina.utilities.manager.SQLManager;
import be.nolwen.lumina.utilities.manager.configuration.ConfigManager;
import be.nolwen.lumina.utilities.manager.configuration.DatabaseManager;
import be.nolwen.lumina.utilities.manager.configuration.LanguageManager;

public class Main extends JavaPlugin {
	
	private static Main instance;
	private Registrar registrar;
	private ConfigurationLoader configurationLoader;
	
	private ConfigManager configManager;
	private LanguageManager languageManager;
	private DatabaseManager databaseManager;
	
	private SQLManager sqlManager;
	private DataManager dataManager;
	
		// ---------------------------------------- \\
	
	@Override
	public void onEnable() {
		instance = this;
		registrar = new Registrar();
		configurationLoader = new ConfigurationLoader();
		
		configManager = new ConfigManager();
		languageManager = new LanguageManager();
		databaseManager = new DatabaseManager();
		
				 // -------------------- \\
		
		configurationLoader.load(configManager);
		configurationLoader.load(languageManager);
		configurationLoader.load(databaseManager);
		
				 // -------------------- \\
		
		sqlManager = new SQLManager();
		dataManager = new DataManager();
		
				 // -------------------- \\
		
		registrar.registerCommands("be.nolwen.lumina.commands");
		registrar.registerListeners("be.nolwen.lumina.listeners");
		
				 // -------------------- \\
		
		Main.getInstance().getSQLManager().connect();
	}
	
	@Override
	public void onDisable() { if(sqlManager != null) sqlManager.disconnect(); }
	
		// ---------------------------------------- \\
	
	public static Main getInstance() { return instance; }
	public File getPluginFile() { return this.getFile(); }

	public ConfigurationLoader getConfigurationLoader() { return configurationLoader; }
	
	public ConfigManager getConfigManager() { return configManager; }
	public LanguageManager getLanguageManager() { return languageManager; }
	public DatabaseManager getDatabaseManager() { return databaseManager; }
	
	public SQLManager getSQLManager() { return sqlManager; }
	public DataManager getDataManager() { return dataManager; }
	
		// ---------------------------------------- \\
	
	public void reload() {
		configurationLoader = new ConfigurationLoader();
		configManager = new ConfigManager();
		languageManager = new LanguageManager();
		sqlManager = new SQLManager();
		
		configurationLoader.load(configManager);
		configurationLoader.load(languageManager);
		configurationLoader.load(databaseManager);
	}

}
