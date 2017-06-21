package level;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import client.Logger;

public class LevelManager implements ILevelManager {

	private HashMap<String, String> files = new HashMap<String, String>();
	private HashMap<String, Level> levels = new HashMap<String, Level>();
	
	FileReader fileReader;
	BufferedReader bufferedReader;
	
	private Level currentLevel;
	
	// Check validity of added level files, creates level with integers
	public boolean init() {
		
		String line = null;
		int width = -1;
		int height = -1;
		int tileWidth = -1;
		int tileHeight = -1;
		String tileSet = null;
		
		Iterator<Map.Entry<String, String>> iterator = files.entrySet().iterator();
		while(iterator.hasNext()) {
			
			try {
				
				Map.Entry<String, String> entry = iterator.next();
				fileReader = new FileReader(entry.getKey());
				bufferedReader = new BufferedReader(fileReader);
				
				// Read the first 12 lines of the level file
				for(int i = 0; i < 12; i++) {
					
					line = bufferedReader.readLine();
					
					// Go to the next line if the current one is empty
					if(line.length() <= 1) continue;
					
					// Get the level width
					if(line.contains("width") && !line.contains("tile")) {
						
						try {
							
							width = Integer.parseInt(line.substring(6, line.length()));
							
						} catch(NumberFormatException e) {
							
							Logger.printError(e.getMessage());
							return false;
							
						}
						
					}
					
					// Get the level height
					if(line.contains("height") && !line.contains("tile")) {
						
						try {
							
							height = Integer.parseInt(line.substring(7, line.length()));
							
						} catch(NumberFormatException e) {
							
							Logger.printError(e.getMessage());
							return false;
							
						}
						
					}
					
					// Get the tile width
					if(line.contains("tilewidth")) {
						
						try {
							
							tileWidth = Integer.parseInt(line.substring(10, line.length()));
							
						} catch(NumberFormatException e) {
							
							Logger.printError(e.getMessage());
							return false;
							
						}
						
					}
					
					// Get the tile height
					if(line.contains("tileheight")) {
						
						try {
							
							tileHeight= Integer.parseInt(line.substring(11, line.length()));
							
						} catch(NumberFormatException e) {
							
							Logger.printError(e.getMessage());
							return false;
							
						}
						
					}
					
					// Get the tileset
					// TODO
					tileSet = "res/level.png";
					
				} // End of reading header/tileset
				
				Level temp = new Level(width, height, tileWidth, tileHeight, tileSet);
				
				for(int i = 0; i < height; i++) {
					
					line = bufferedReader.readLine();
					String[] data = line.split(",");
					
					for(int j = 0; j < width; j++) {
						
						try {
							
							temp.setTile(j, i, Integer.parseInt(data[j]));
							
						} catch(NumberFormatException e) {
							
							Logger.printError(e.getMessage());
							return false;
							
						}
					}
					
				}
				
				bufferedReader.close();
				fileReader.close();
				
				Logger.printDebug("Adding level " + entry.getValue());
				levels.put(entry.getValue(), temp);
				
			} catch(FileNotFoundException e) {
				
				Logger.printError(e.getMessage());
				return false;
				
			} catch(IOException e) {
				
				Logger.printError(e.getMessage());
				
			}
			
		}
		
		return true;
		
	}

	// Add a file and a corresponding level name
	public void addLevel(String fileName, String levelName) {

		if(!isLevelAdded(fileName))
			files.put(fileName, levelName);
		else
			Logger.printError(fileName + " has already been added - level files must be unique.");

	}

	// Grab a level and turn integers into blocks
	public boolean loadLevel(String levelName) {

		Logger.printDebug("Attempting to load level " + levelName);
		Level level = getLevel(levelName);
		
		if(level == null) 
			return false;
		else {
			
			level.initBlockMap();
			
		}
		
		return true;
		
	}
	
	// Check if a level has had its blocks loaded
	public boolean isLevelLoaded(String levelName) {

		if(getLevel(levelName) != null)
			return getLevel(levelName).blocksLoaded();
		
		return false;
		
	}

	// Return a level from levels map
	public Level getLevel(String levelName) {
		
		return levels.get(levelName);
		
	}

	// Check if a level has been added to files
	public boolean isLevelAdded(String fileName) {

		Iterator<Map.Entry<String, String>> iterator = files.entrySet().iterator();
		while(iterator.hasNext()) {
			
			Map.Entry<String, String> entry = iterator.next();
			if(entry.getKey() == fileName) return true;
			
		}
		
		return false;
		
	}
	
	public int addedSize() {
		
		return files.size();
		
	}
	
	public int loadedSize() {
		
		return levels.size();
		
	}
	
	public void setCurrentLevel(String levelName) { 
		
		if(!isLevelLoaded(levelName)) loadLevel(levelName); 
		
		currentLevel = getLevel(levelName);
		
	}
	
	public String getNextLevelName() {
		
		if(currentLevel == getLevel(Level.LEVEL_00_NAME)) return Level.LEVEL_01_NAME;
		if(currentLevel == getLevel(Level.LEVEL_01_NAME)) return Level.LEVEL_02_NAME;
		if(currentLevel == getLevel(Level.LEVEL_02_NAME)) return Level.LEVEL_03_NAME;
		if(currentLevel == getLevel(Level.LEVEL_03_NAME)) return Level.LEVEL_04_NAME;
		if(currentLevel == getLevel(Level.LEVEL_04_NAME)) return Level.LEVEL_05_NAME;
		if(currentLevel == getLevel(Level.LEVEL_05_NAME)) return Level.LEVEL_06_NAME;
		if(currentLevel == getLevel(Level.LEVEL_06_NAME)) return Level.LEVEL_07_NAME;
		if(currentLevel == getLevel(Level.LEVEL_07_NAME)) return Level.LEVEL_08_NAME;
		if(currentLevel == getLevel(Level.LEVEL_08_NAME)) return Level.LEVEL_09_NAME;
		if(currentLevel == getLevel(Level.LEVEL_09_NAME)) return Level.LEVEL_10_NAME;
		if(currentLevel == getLevel(Level.LEVEL_10_NAME)) return Level.LEVEL_11_NAME;
		if(currentLevel == getLevel(Level.LEVEL_11_NAME)) return Level.LEVEL_12_NAME;
		if(currentLevel == getLevel(Level.LEVEL_12_NAME)) return Level.LEVEL_13_NAME;
		
		return Level.LEVEL_00_NAME;
		
	}
	
	public Level getCurrentLevel() { return currentLevel; }

}