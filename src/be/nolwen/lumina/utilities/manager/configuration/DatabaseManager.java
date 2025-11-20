package be.nolwen.lumina.utilities.manager.configuration;

import be.nolwen.lumina.utilities.annotation.configuration.Configuration;

public class DatabaseManager {
	
	@Configuration(FILE = "Database", KEY = "HOSTNAME")
	public String hostname;
	
	@Configuration(FILE = "Database", KEY = "USERNAME")
	public String username;
	
	@Configuration(FILE = "Database", KEY = "PASSWORD")
	public String password;
	
	@Configuration(FILE = "Database", KEY = "NAME")
	public String name;
	
		// ---------------------------------------- \\
	
	public String getHostname() { return hostname; }
	public String getUsername() { return username; }
	public String getPassword() { return password; }
	public String getName() { return name; }
	
}
