package be.nolwen.lumina.utilities.manager.updater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.bukkit.Bukkit;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import be.nolwen.lumina.Main;

public class Updater {
	
	public void checkForUpdate() {
		if(Main.getInstance().getDataManager().isUpdateFound()) return;
		
		Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
			ReleaseInformation latest = getLatestRelease();
			if(latest == null) {
				Main.getInstance().getLogger().warning("⚠️ | Latest update retrieval failed !");
				return;
			}
			
			String current = Main.getInstance().getConfigManager().getVersion();
			if(!isNewerVersion(latest.releaseTag, current)) {
				Main.getInstance().getLogger().info("✅ | Plugin already running on latest version !");
				return;
			}
			
			Main.getInstance().getLogger().info(String.format("💡 | A new update is available : %s", latest.releaseTag));
			Main.getInstance().getLogger().info("♻️ | Downloading update ...");
			
			if(downloadUpdate(latest.downloadURL)) {
				Main.getInstance().getLogger().info("✅ | Update successfully downloaded !");
				backup();
				Main.getInstance().getLogger().info("✅ | Restart the server to apply it !");
				Main.getInstance().getDataManager().setUpdateFound(true);
			} else { Main.getInstance().getLogger().warning("⚠️ | Download failed !"); }
		});
	}

		// ---------------------------------------- \\
	
	private boolean isNewerVersion(String newVersion, String currentVersion) {
		try {
			String newVersionFormatted = newVersion.replace("v", "");
			String currentVersionFormatted = currentVersion.replace("v", "");
			
			String[] newVersionString = newVersionFormatted.split("\\.");
			String[] currentVersionString = currentVersionFormatted.split("\\.");
			
			for(int index = 0; index < Math.min(newVersionString.length, currentVersionString.length); index++) {
				int newIndex = Integer.parseInt(newVersionString[index]);
				int currentIndex = Integer.parseInt(currentVersionString[index]);
				
				if(newIndex > currentIndex) return true;
				if(newIndex < currentIndex) return false;
			}
			
			return newVersionString.length > currentVersionString.length;
		} catch(Exception exception) { return !newVersion.equals(currentVersion); }
	}
	
	private ReleaseInformation getLatestRelease() {
		try {
			
			HttpURLConnection connection = (HttpURLConnection) URI.create("https://api.github.com/repos/NolwenDEV/Lumina/releases/latest").toURL().openConnection();
			connection.setRequestProperty("Accept", "application/vnd.github+json");
			connection.setRequestProperty("User-Agent", "NolwenDEV");
			
			try(BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
				JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
				return ReleaseInformation.fromJson(jsonObject).orElse(null);
			}
		} catch (IOException exception) {
			Main.getInstance().getLogger().warning(String.format("⚠️ | An error has occured while retrieving latest release : %s", exception.getMessage()));
		}
		
		return null;
	}
	
	private boolean downloadUpdate(String url) {
		try(InputStream input = URI.create(url).toURL().openStream()) {
			Path target = Paths.get("plugins/Lumina.jar");
			Files.copy(input, target, StandardCopyOption.REPLACE_EXISTING);
			
			return true;
		} catch(Exception exception) {
			Main.getInstance().getLogger().warning(String.format("⚠️ | An error has occured while downloading latest release : %s", exception.getMessage()));
			return false;
		}
	}
	
	private void backup() {
		try {
			Path pluginFolder = Paths.get("plugins/Lumina");
			Path backupFolder = pluginFolder.resolve("Backup").resolve(Main.getInstance().getConfigManager().getVersion());
			
			moveFile(pluginFolder.resolve("Configuration.yml"), backupFolder);
			moveFile(pluginFolder.resolve("Language.yml"), backupFolder);
		    
		    Main.getInstance().getLogger().info(
		    		String.format("✅ | Old configuration files have been moved to '/plugins/Lumina/Backup/%s/'", Main.getInstance().getConfigManager().getVersion())
		    );
		} catch(Exception exception) {
			Main.getInstance().getLogger().warning(String.format("⚠️ | An error has occured while backing-up configuration files : %s", exception.getMessage()));
		}
	}
	
	private void moveFile(Path source, Path destinationDir) throws IOException {
		Files.createDirectories(destinationDir);
		Path destination = destinationDir.resolve(source.getFileName());
		
		Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
	}

}
