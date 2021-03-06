package de.hexagonsoftware.engine.resources;

import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import de.hexagonsoftware.engine.HexagonEngine;
import de.hexagonsoftware.engine.util.HEResourceLoadException;

/**
 * Used for loading Resources from a JSON file
 * 
 * @author Felix Eckert
 * */
public class JSONResourceLoader {
	private static Logger logger = LogManager.getLogger("JSONResourceLoader");
	
	/**
	 * Loads all resources listed in the specified file.
	 * 
	 * @param file The JSON file from which to load them.
	 * */
	public static void loadResources(String file) {
		logger.info("Loading Resources from JSON file "+file);
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new InputStreamReader(HexagonEngine.getGame().getClass().getResourceAsStream(file)));
		JsonObject resourceFile = gson.fromJson(reader, JsonObject.class);
		
		if (resourceFile.has("textures")) {
			loadTextures(resourceFile);
		}
		if (resourceFile.has("sounds")) {
			loadSounds(resourceFile);
		}
		if (resourceFile.has("fonts")) {
			loadFonts(resourceFile);
		}
	}

	/**
	 * @param resourceFile The resourceFile from which to grab the texture list
	 * */
	public static void loadTextures(JsonObject resourceFile) {
		JsonObject textures = resourceFile.get("textures").getAsJsonObject();
		String assetsRoot   = HexagonEngine.getEngineConfig().getAssetsRoot();
		
		if (assetsRoot == null)
			assetsRoot = "";
		
		int loadedTextures = 0;
		
		for (String texture : textures.keySet()) {
			loadedTextures++;
			HexagonEngine.HE_RES_MANAGER.addResource(texture, new TextureResource(assetsRoot+textures.get(texture).getAsString(), true));
		}
		
		logger.info("Loaded "+loadedTextures+" texture(s)");
	}
	
	/**
	 * @param resourceFile The resourceFile from which to grab the sound list
	 * */
	public static void loadSounds(JsonObject resourceFile) {
		JsonObject sounds = resourceFile.get("sounds").getAsJsonObject();
		String assetsRoot   = HexagonEngine.getEngineConfig().getAssetsRoot();
		
		if (assetsRoot == null)
			assetsRoot = "";
		
		int loadedSounds = 0;
		
		for (String sound : sounds.keySet()) {
			loadedSounds++;
			HexagonEngine.HE_RES_MANAGER.addResource(sound, new SoundResource(assetsRoot+sounds.get(sound).getAsString(), true));
		}
		
		logger.info("Loaded "+loadedSounds+" sound(s)");
	}
	
	/**
	 * @param resourceFile The resourceFile from which to grab the font list
	 * */
	public static void loadFonts(JsonObject resourceFile) {
		JsonObject fonts    = resourceFile.get("fonts").getAsJsonObject();
		String assetsRoot   = HexagonEngine.getEngineConfig().getAssetsRoot();
		
		if (assetsRoot == null)
			assetsRoot = "";
		
		int loadedFonts = 0;
		
		for (String font : fonts.keySet()) {
			loadedFonts++;
			JsonObject fontObject = fonts.get(font).getAsJsonObject();
			HexagonEngine.HE_RES_MANAGER.addResource(font, new FontResource(assetsRoot+fontObject.get("path").getAsString(), fontObject.get("size").getAsFloat(), fontObject.get("type").getAsInt(), true));
		}
		
		logger.info("Loaded "+loadedFonts+" font(s)");
	}
}
