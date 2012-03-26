package uk.co.jacekk.bukkit.NoFloatingTrees.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.file.YamlConfiguration;

import uk.co.jacekk.bukkit.NoFloatingTrees.NoFloatingTrees;

public class NoFloatingTreesConfig {
	
	private YamlConfiguration config;
	private HashMap<String, Object> configDefaults = new HashMap<String, Object>();
	
	public NoFloatingTreesConfig(File configFile, NoFloatingTrees plugin){
		this.config = new YamlConfiguration();
		
		this.configDefaults.put("use-logblock", true);
		
		if (configFile.exists()){
			try {
				this.config.load(configFile);
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		
		boolean updateNeeded = false;
		
		for (String key : this.configDefaults.keySet()){
			if (this.config.contains(key) == false){
				this.config.set(key, this.configDefaults.get(key));
				
				updateNeeded = true;
			}
		}
		
		if (updateNeeded){
			try {
				this.config.save(configFile);
				plugin.log.info("The config.yml file has been updated.");
			} catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public boolean getBoolean(String key){
		if (this.configDefaults.containsKey(key) == false){
			return false;
		}
		
		return this.config.getBoolean(key, (Boolean) this.configDefaults.get(key));
	}
	
	public int getInt(String key){
		if (this.configDefaults.containsKey(key) == false){
			return 0;
		}
		
		return this.config.getInt(key, (Integer) this.configDefaults.get(key));
	}
	
}