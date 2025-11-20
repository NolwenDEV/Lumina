package be.nolwen.lumina.utilities.builder;

import java.util.Map;

import org.bukkit.command.CommandSender;

import be.nolwen.lumina.Main;

public class MessageBuilder {

	public static String format(String raw) { return format(raw, Map.of()); }
	public static boolean error(CommandSender sender, String raw) { return error(sender, raw, Map.of()); }
	
		// ---------------------------------------- \\
	
	public static String format(String raw, Map<String, String> values) {
		String formatted = raw;
		
		if(formatted.contains("{PREFIX}")) formatted = formatted.replace("{PREFIX}", Main.getInstance().getConfigManager().getPrefix());
		for(Map.Entry<String, String> value : values.entrySet()) {
			formatted = formatted.replace(String.format("{%s}", value.getKey().toUpperCase()), value.getValue());
		}
		
		return formatted;
	}
	
	public static boolean error(CommandSender sender, String raw, Map<String, String> values) {
		sender.sendMessage(format(raw, values));
		return true;
	}
	
}
