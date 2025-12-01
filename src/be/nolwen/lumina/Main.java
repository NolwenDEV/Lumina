package be.nolwen.lumina;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import be.nolwen.lumina.utilities.annotation.configuration.ConfigurationLoader;
import be.nolwen.lumina.utilities.annotation.registrar.Registrar;
import be.nolwen.lumina.utilities.manager.DataManager;
import be.nolwen.lumina.utilities.manager.SQLManager;
import be.nolwen.lumina.utilities.manager.configuration.ConfigManager;
import be.nolwen.lumina.utilities.manager.configuration.DatabaseManager;
import be.nolwen.lumina.utilities.manager.configuration.DiscordManager;
import be.nolwen.lumina.utilities.manager.configuration.LanguageManager;
import be.nolwen.lumina.utilities.manager.updater.Updater;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main extends JavaPlugin {
	
	private static Main instance;
	private JDA discordAPI;
	private Registrar registrar;
	private ConfigurationLoader configurationLoader;
	
	private ConfigManager configManager;
	private LanguageManager languageManager;
	private DatabaseManager databaseManager;
	private DiscordManager discordManager;
	
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
		discordManager = new DiscordManager();
		
				 // -------------------- \\
		
		configurationLoader.load(configManager);
		configurationLoader.load(languageManager);
		configurationLoader.load(databaseManager);
		configurationLoader.load(discordManager);
		
				 // -------------------- \\
		
		sqlManager = new SQLManager();
		dataManager = new DataManager();
		
				 // -------------------- \\
		
		if (discordManager.isEnabled()) {
            discordAPI = JDABuilder.createDefault(discordManager.getToken())
            		.setStatus(discordManager.getOnlineStatus())
            		.setActivity(discordManager.getActivityStatus())
            		.enableIntents(GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT)
            		.build();
        }
		
				 // -------------------- \\
		
		registrar.registerCommands("be.nolwen.lumina.commands");
		registrar.registerListeners("be.nolwen.lumina.listeners");
		
				 // -------------------- \\
		
		Main.getInstance().getSQLManager().connect();
		Bukkit.getScheduler().runTaskTimer(instance, () -> {
			new Updater().checkForUpdate();
		}, 0L, (6 * 60 * 60 * 20L));
	}
	
	@Override
	public void onDisable() { if(sqlManager != null) sqlManager.disconnect(); }
	
		// ---------------------------------------- \\
	
	public static Main getInstance() { return instance; }
	public File getPluginFile() { return this.getFile(); }

	public JDA getDiscordAPI() { return discordAPI; }
	public ConfigurationLoader getConfigurationLoader() { return configurationLoader; }
	
	public ConfigManager getConfigManager() { return configManager; }
	public LanguageManager getLanguageManager() { return languageManager; }
	public DatabaseManager getDatabaseManager() { return databaseManager; }
	public DiscordManager getDiscordManager() { return discordManager; }
	
	public SQLManager getSQLManager() { return sqlManager; }
	public DataManager getDataManager() { return dataManager; }
	
		// ---------------------------------------- \\
	
	public void reload() {
		configurationLoader = new ConfigurationLoader();
		configManager = new ConfigManager();
		languageManager = new LanguageManager();
		discordManager = new DiscordManager();
		sqlManager = new SQLManager();
		
		configurationLoader.load(configManager);
		configurationLoader.load(languageManager);
		configurationLoader.load(databaseManager);
		configurationLoader.load(discordManager);
	}

}
