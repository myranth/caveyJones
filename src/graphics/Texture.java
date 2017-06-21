package graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import client.Logger;

public class Texture {

//	public static final String LEVEL_TILES_00_FILE = "../res/level00.png";
//	public static final String GLOW_00_FILE = "../res/glow.png";
//	public static final String BREAK_00_FILE = "../res/break.png";
//	public static final String ARROWS_00_FILE = "../res/arrows.png";
//	public static final String DOORS_00_FILE = "../res/doors.png";
//	public static final String TITLE_SCREEN_FILE = "../res/titleScreen.png";
	
	public static final String LEVEL_TILES_00_FILE = "res/level00.png";
	public static final String GLOW_00_FILE = "res/glow.png";
	public static final String BREAK_00_FILE = "res/break.png";
	public static final String ARROWS_00_FILE = "res/arrows.png";
	public static final String DOORS_00_FILE = "res/doors.png";
	public static final String TITLE_SCREEN_FILE = "res/titleScreen.png";
	
	public static final String LEVEL_TILES_00_NAME = "levelTiles00";
	public static final String GLOW_00_NAME = "glow00";
	public static final String BREAK_00_NAME = "break00";
	public static final String ARROWS_00_NAME = "arrows00";
	public static final String DOORS_00_NAME = "doors00";
	public static final String TITLE_SCREEN_NAME = "titleScreen";
	
	private BufferedImage texture;
	
	public Texture(String fileName) {
		
		try {
			
			texture = ImageIO.read(new File(fileName));
			
		} catch(IOException e) {
			
			Logger.printError(e.getMessage());
			
		}
		
	}
	
	public BufferedImage getImage() {
		
		return texture;
		
	}
	
}