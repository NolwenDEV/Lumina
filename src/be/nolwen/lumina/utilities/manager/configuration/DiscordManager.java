package be.nolwen.lumina.utilities.manager.configuration;

import be.nolwen.lumina.utilities.annotation.configuration.Configuration;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Activity.ActivityType;

public class DiscordManager {
	
	@Configuration(FILE = "Discord", KEY = "ENABLED")
	private boolean enabled;
	
	@Configuration(FILE = "Discord", KEY = "TOKEN")
	private String token;

				// -------------------- \\
	
	@Configuration(FILE = "Discord", KEY = "INFORMATION.STATUS.TYPE")
	private String statusTypeInformation;
	
	@Configuration(FILE = "Discord", KEY = "INFORMATION.ACTIVITY.TYPE")
	private String activityTypeInformation;
	
	@Configuration(FILE = "Discord", KEY = "INFORMATION.ACTIVITY.VALUE")
	private String activityValueInformation;
	
		// ---------------------------------------- \\
	
	public boolean isEnabled() { return enabled; }
	public String getToken() { return token; }
	
				// -------------------- \\
	
	public OnlineStatus getOnlineStatus() { return OnlineStatus.valueOf(statusTypeInformation.toUpperCase()); }
	public Activity getActivityStatus() { return Activity.of(ActivityType.valueOf(activityTypeInformation.toUpperCase()), activityValueInformation); }
	
}
