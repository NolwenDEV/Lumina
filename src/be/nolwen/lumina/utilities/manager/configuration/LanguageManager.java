package be.nolwen.lumina.utilities.manager.configuration;

import java.util.HashMap;
import java.util.Map;

import be.nolwen.lumina.utilities.annotation.configuration.Configuration;

public class LanguageManager {
	
	@Configuration(FILE = "Language", KEY = "MESSAGE.HOME.CREATED")
	private String homeAddedMessage;
	
	@Configuration(FILE = "Language", KEY = "MESSAGE.HOME.DELETED")
	private String homeRemovedMessage;
	
	@Configuration(FILE = "Language", KEY = "MESSAGE.HOME.LIST")
	private Map<String, String> listHomeMessage = new HashMap<>();
	
	@Configuration(FILE = "Language", KEY = "MESSAGE.HOME.TELEPORTED")
	private String homeTeleportedMessage;
	
	@Configuration(FILE = "Language", KEY = "MESSAGE.HOME.HOVER")
	private String homeHoverMessage;
	
				// -------------------- \\
	
	@Configuration(FILE = "Language", KEY = "MESSAGE.FILTER.ENABLED")
	private String filterEnabledMessage;
	
	@Configuration(FILE = "Language", KEY = "MESSAGE.FILTER.DISABLED")
	private String filterDisabledMessage;
	
				// -------------------- \\
	
	@Configuration(FILE = "Language", KEY = "MESSAGE.BACK.REMINDER")
	private String backReminderMessage;
	
	@Configuration(FILE = "Language", KEY = "MESSAGE.TRASH.COUNTER")
	private String trashCounterMessage;
	
				// -------------------- \\
	
	@Configuration(FILE = "Language", KEY = "ERROR.PLAYER_ONLY_COMMAND")
	private String playerOnlyCommandError;
	
	@Configuration(FILE = "Language", KEY = "ERROR.FEATURE_DISABLED")
	private String featureDisabledError;
	
	@Configuration(FILE = "Language", KEY = "ERROR.DATABASE_DISABLED")
	private String databaseDisabledError;
	
	@Configuration(FILE = "Language", KEY = "ERROR.BACK.NOT_DEAD")
	private String backNotDeadError;
	
	@Configuration(FILE = "Language", KEY = "ERROR.FILTER.NO_DUPLICATE_ITEM")
	private String filterNoDuplicateItemError;
	
	@Configuration(FILE = "Language", KEY = "ERROR.HOME.EMPTY_LIST")
	private String homeEmptyError;
	
	@Configuration(FILE = "Language", KEY = "ERROR.HOME.NOT_FOUND")
	private String homeNotFoundError;
	
	@Configuration(FILE = "Language", KEY = "ERROR.BACKPACK.SAVE_FAIL")
	private String backpackSaveFailError;
	
				// -------------------- \\
	
	@Configuration(FILE = "Language", KEY = "ERROR.COMMAND_USAGE.LUMINA")
	private String luminaUsageError;
	
	@Configuration(FILE = "Language", KEY = "ERROR.COMMAND_USAGE.SET_HOME")
	private String setHomeUsageError;
	
	@Configuration(FILE = "Language", KEY = "ERROR.COMMAND_USAGE.DEL_HOME")
	private String delHomeUsageError;
	
	@Configuration(FILE = "Language", KEY = "ERROR.COMMAND_USAGE.HOME")
	private String homeUsageError;
	
				 // -------------------- \\
	
	@Configuration(FILE = "Language", KEY = "EVENT.JOIN")
	private String eventJoin;
	
	@Configuration(FILE = "Language", KEY = "EVENT.QUIT")
	private String eventQuit;
	
	@Configuration(FILE = "Language", KEY = "EVENT.DEATH")
	private String eventDeath;
	
				// -------------------- \\
	
	@Configuration(FILE = "Language", KEY = "GUI.FILTER.TITLE")
	private String guiFilterTitle;
	
	@Configuration(FILE = "Language", KEY = "GUI.FILTER.BUTTON.ENABLE")
	private String guiFilterEnableButton;
	
	@Configuration(FILE = "Language", KEY = "GUI.FILTER.BUTTON.DISABLE")
	private String guiFilterDisableButton;
	
	@Configuration(FILE = "Language", KEY = "GUI.FILTER.BUTTON.CLEAR")
	private String guiFilterClearButton;
	
	@Configuration(FILE = "Language", KEY = "GUI.BACKPACK.TITLE")
	private String guiBackpackTitle;
	
	@Configuration(FILE = "Language", KEY = "GUI.TRASH.TITLE")
	private String guiTrashTitle;
	
		// ---------------------------------------- \\
	
	public String getHomeAddedMessage() { return homeAddedMessage; }
	public String getHomeRemovedMessage() { return homeRemovedMessage; }
	public Map<String, String> getListHomesMessage() { return listHomeMessage; }
	public String getHomeTeleportedMessage() { return homeTeleportedMessage; }
	public String getHomeHoverMessage() { return homeHoverMessage; }
	
				// -------------------- \\
	
	public String getFilterEnabledMessage() { return filterEnabledMessage; }
	public String getFilterDisabledMessage() { return filterDisabledMessage; }
	
				// -------------------- \\
	
	public String getBackReminderMessage() { return backReminderMessage; }
	public String getTrashCounterMessage() { return trashCounterMessage; }
	
				// -------------------- \\
	
	public String getPlayerOnlyCommandError() { return playerOnlyCommandError; }
	public String getFeatureDisabledError() { return featureDisabledError; }
	public String getDatabaseDisabledError() { return databaseDisabledError; }
	public String getBackNotDeadError() { return backNotDeadError; }
	public String getFilterNoDuplicateItemError() { return filterNoDuplicateItemError; }
	public String getHomeEmptyError() { return homeEmptyError; }
	public String getHomeNotFoundError() { return homeNotFoundError; }
	public String getBackpackSaveFailError() { return backpackSaveFailError; }
	
				// -------------------- \\
	
	public String getLuminaUsageError() { return luminaUsageError; }
	public String getSetHomeUsageError() { return setHomeUsageError; }
	public String getDelHomeUsageError() { return delHomeUsageError; }
	public String getHomeUsageError() { return homeUsageError; }
	
				// -------------------- \\
	
	public String getEventJoin() { return eventJoin; }
	public String getEventQuit() { return eventQuit; }
	public String getEventDeath() { return eventDeath; }
	
				// -------------------- \\
	
	public String getGUIFilterTitle() { return guiFilterTitle; }
	public String getGUIFilterEnableButton() { return guiFilterEnableButton; }
	public String getGUIFilterDisableButton() { return guiFilterDisableButton; }
	public String getGUIFilterClearButton() { return guiFilterClearButton; }
	public String getGUIBackpackTitle() { return guiBackpackTitle; }
	public String getGUITrashTitle() { return guiTrashTitle; }

}
