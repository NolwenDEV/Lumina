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
	
	@Configuration(FILE = "Language", KEY = "MESSAGE.FILTER.TOGGLE.ENABLED")
	private String filterToggleEnabledMessage;
	
	@Configuration(FILE = "Language", KEY = "MESSAGE.FILTER.TOGGLE.DISABLED")
	private String filterToggleDisabledMessage;
	
	@Configuration(FILE = "Language", KEY = "MESSAGE.FILTER.DESTROYER.ENABLED")
	private String filterDestroyerEnabledMessage;
	
	@Configuration(FILE = "Language", KEY = "MESSAGE.FILTER.DESTROYER.DISABLED")
	private String filterDestroyerDisabledMessage;
	
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
	
	@Configuration(FILE = "Language", KEY = "GUI.FILTER.BUTTON.SEPARATOR")
	private Map<String, String> guiFilterSeparatorButton;
	
	@Configuration(FILE = "Language", KEY = "GUI.FILTER.BUTTON.TOGGLE.ENABLE")
	private Map<String, String> guiFilterToggleEnableButton;
	
	@Configuration(FILE = "Language", KEY = "GUI.FILTER.BUTTON.TOGGLE.DISABLE")
	private Map<String, String> guiFilterToggleDisableButton;
	
	@Configuration(FILE = "Language", KEY = "GUI.FILTER.BUTTON.DESTROYER.ENABLE")
	private Map<String, String> guiFilterDestroyerEnableButton;
	
	@Configuration(FILE = "Language", KEY = "GUI.FILTER.BUTTON.DESTROYER.DISABLE")
	private Map<String, String> guiFilterDestroyerDisableButton;
	
	@Configuration(FILE = "Language", KEY = "GUI.FILTER.BUTTON.CLEAR")
	private Map<String, String> guiFilterClearButton;
	
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
	
	public String getFilterToggleEnabledMessage() { return filterToggleEnabledMessage; }
	public String getFilterToggleDisabledMessage() { return filterToggleDisabledMessage; }
	public String getFilterDestroyerEnabledMessage() { return filterDestroyerEnabledMessage; }
	public String getFilterDestroyerDisabledMessage() { return filterDestroyerDisabledMessage; }
	
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
	public Map<String, String> getGUIFilterSeparatorButton() { return guiFilterSeparatorButton; }
	public Map<String, String> getGUIFilterToggleEnableButton() { return guiFilterToggleEnableButton; }
	public Map<String, String> getGUIFilterToggleDisableButton() { return guiFilterToggleDisableButton; }
	public Map<String, String> getGUIFilterDestroyerEnableButton() { return guiFilterDestroyerEnableButton; }
	public Map<String, String> getGUIFilterDestroyerDisableButton() { return guiFilterDestroyerDisableButton; }
	public Map<String, String> getGUIFilterClearButton() { return guiFilterClearButton; }
	public String getGUIBackpackTitle() { return guiBackpackTitle; }
	public String getGUITrashTitle() { return guiTrashTitle; }

}
