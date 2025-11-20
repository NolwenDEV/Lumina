package be.nolwen.lumina.commands.home;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.nolwen.lumina.Main;
import be.nolwen.lumina.utilities.annotation.registrar.CommandRegistrar;
import be.nolwen.lumina.utilities.builder.MessageBuilder;
import be.nolwen.lumina.utilities.builder.TextComponentBuilder;
import be.nolwen.lumina.utilities.enumeration.Query;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

@CommandRegistrar(NAME = "homes")
public class HomeList implements CommandExecutor {
	
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
		if(!command.getName().equals("homes")) return true;
		if(!Main.getInstance().getConfigManager().isHomeModule()) return MessageBuilder.error(sender, Main.getInstance().getLanguageManager().getFeatureDisabledError());
		if(!Main.getInstance().getConfigManager().isDatabaseModule()) return MessageBuilder.error(sender, Main.getInstance().getLanguageManager().getDatabaseDisabledError());
		if(!(sender instanceof Player)) return MessageBuilder.error(sender, Main.getInstance().getLanguageManager().getPlayerOnlyCommandError());
		
		Player player = (Player) sender;
		
		Main.getInstance().getSQLManager().executeQuery(Query.RETRIEVE_HOME, (result) -> {
			if(!result.isEmpty()) {						
				player.sendMessage(MessageBuilder.format(Main.getInstance().getLanguageManager().getListHomesMessage().get("MESSAGE")));
				
				List<TextComponent> textComponents = new ArrayList<>();
				for(Map<String, Object> home : result) {
					textComponents.add(new TextComponentBuilder().setMessage(
							MessageBuilder.format(
									Main.getInstance().getLanguageManager().getListHomesMessage().get("HOMES"),
									Map.of(
											"WORLD", home.get("WORLD").toString(),
											"HOME", home.get("NAME").toString(),
											"X", String.format("%.2f", (double) home.get("X")),
											"Y", String.format("%.2f", (double) home.get("Y")),
											"Z", String.format("%.2f", (double) home.get("Z"))
									)
							)
					).setHoverEvent(HoverEvent.Action.SHOW_TEXT, MessageBuilder.format(
							Main.getInstance().getLanguageManager().getHomeHoverMessage(), Map.of("HOME", home.get("NAME").toString())
					)).setClickAction(ClickEvent.Action.RUN_COMMAND, String.format("/home %s", home.get("NAME"))).build());
				}
				
				for(TextComponent textComponent : textComponents) { player.spigot().sendMessage(new TextComponentBuilder().compose(textComponent).build()); }
			} else { player.sendMessage(MessageBuilder.format(Main.getInstance().getLanguageManager().getHomeNotFoundError())); }
		}, player.getUniqueId().toString(), player.getName());
		
		return true;
    }

}
