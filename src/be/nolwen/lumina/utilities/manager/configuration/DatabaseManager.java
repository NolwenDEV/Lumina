package be.nolwen.lumina.utilities.manager.configuration;

import be.nolwen.lumina.utilities.annotation.configuration.Configuration;

public class DatabaseManager {
	
	@Configuration(FILE = "Database", KEY = "ENABLED")
	private boolean enabled;
	
	@Configuration(FILE = "Database", KEY = "HOSTNAME")
	private String hostname;
	
	@Configuration(FILE = "Database", KEY = "USERNAME")
	private String username;
	
	@Configuration(FILE = "Database", KEY = "PASSWORD")
	private String password;
	
	@Configuration(FILE = "Database", KEY = "NAME")
	private String name;
	
		// ---------------------------------------- \\
	
	public boolean isEnabled() { return enabled; }
	public String getHostname() { return hostname; }
	public String getUsername() { return username; }
	public String getPassword() { return password; }
	public String getName() { return name; }
	
}
