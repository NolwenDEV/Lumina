package be.nolwen.lumina.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import be.nolwen.lumina.Main;
import be.nolwen.lumina.utilities.annotation.registrar.CommandRegistrar;
import be.nolwen.lumina.utilities.builder.TextComponentBuilder;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;

@CommandRegistrar(NAME = "Lumina")
public class Lumina implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
		if(!command.getName().equalsIgnoreCase("lumina")) return true;
		
		if(arguments.length == 0) {
			sender.spigot().sendMessage(new TextComponentBuilder().compose(
					new TextComponentBuilder().setMessage("§8§m------------------------------" + "§r\n\n").build(),
					new TextComponentBuilder().setMessage("  §8● §7/§flumina §dreload §8» §7Reload Configuration Files" + "\n")
						.setHoverEvent(HoverEvent.Action.SHOW_TEXT, "§7Click to execute command §8» §7/§flumina §dreload")
						.setClickAction(ClickEvent.Action.RUN_COMMAND, "/lumina reload")
						.build(),
					new TextComponentBuilder().setMessage("  §8● §7/§flumina §ddatabase §8» §7Check Database Statut")
						.setHoverEvent(HoverEvent.Action.SHOW_TEXT, "§7Click to execute command §8» §7/§flumina §ddatabase")
						.setClickAction(ClickEvent.Action.RUN_COMMAND, "/lumina database")
						.build(),
					new TextComponentBuilder().setMessage("§r\n\n" + "§8§m------------------------------").build()
			).build());
			
			return true;
		} else {
			switch(arguments[0]) {
				case "reload":
					Main.getInstance().reload();
					sender.sendMessage("§dLumina §8● §7Configuration has been §asuccessfully §7reloaded !");
					break;
				case "database":
					sender.sendMessage(
							String.format("§dLumina §8● §7Database is currently %s §7!", (Main.getInstance().getSQLManager().isConnected() ? "§aonline" : "§coffline"))
					);
					if(!Main.getInstance().getConfigManager().isDatabaseModule()) {
						sender.sendMessage("§dLumina §8● §cDatabase is disabled in 'Configuration.yml', features using database have been disabled !");
					} else if(!Main.getInstance().getSQLManager().isConnected()) {
						sender.sendMessage("§dLumina §8● §7No worries if database if §coffline§7, it'll automatically be reconnected at next SQL request !");
					}
					break;
			}
		}
		
		return true;
	}

}
