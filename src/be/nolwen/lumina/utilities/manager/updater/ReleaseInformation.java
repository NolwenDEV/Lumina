package be.nolwen.lumina.utilities.manager.updater;

import java.util.Optional;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ReleaseInformation {
	
	public final String releaseTag;
	public final String downloadURL;
	
		// ---------------------------------------- \\
	
	public ReleaseInformation(String releaseTag, String downloadURL) {
		this.releaseTag = releaseTag;
		this.downloadURL = downloadURL;
	}
	
		// ---------------------------------------- \\
	
	public static Optional<ReleaseInformation> fromJson(JsonObject json) {
		try {
			String tag = json.get("tag_name").getAsString();
			
			JsonArray assets = json.getAsJsonArray("assets");
			if(assets.size() == 0) return Optional.empty();
			
			JsonObject asset = assets.get(0).getAsJsonObject();
			String download = asset.get("browser_download_url").getAsString();
			
			return Optional.of(new ReleaseInformation(tag, download));
		} catch(Exception exception) { Optional.empty(); }
		
		return Optional.empty();
	}

}
