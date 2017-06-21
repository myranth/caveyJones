package graphics;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import client.Logger;
import block.Block;

public class GraphicsManager implements IGraphicsManager {

	private Map<String, Texture> textures = new HashMap<String, Texture>();
	private Map<String, Sprite> sprites = new HashMap<String, Sprite>();
	
	public boolean init() {
		
		if(!addTexture(Texture.LEVEL_TILES_00_FILE, Texture.LEVEL_TILES_00_NAME))
			return false;
		
		if(!addTexture("res/charRight.png", "caveyJones"))
			return false;
		
		if(!addTexture(Texture.GLOW_00_FILE, Texture.GLOW_00_NAME))
			return false;
		
		if(!addTexture(Texture.BREAK_00_FILE, Texture.BREAK_00_NAME))
			return false;
		
		if(!addTexture(Texture.ARROWS_00_FILE, Texture.ARROWS_00_NAME))
			return false;
		
		if(!addTexture(Texture.DOORS_00_FILE, Texture.DOORS_00_NAME))
			return false;
		
		if(!addTexture(Texture.TITLE_SCREEN_FILE, Texture.TITLE_SCREEN_NAME))
			return false;
		
		if(!addSprite(getTexture("caveyJones"), 0, 0, 32, 32, "caveySprite"))
			return false;
		
		if(!addSprite(getTexture(Texture.ARROWS_00_NAME), 0, 0, 32, 32, "arrowRight"))
			return false;
		
		if(!addSprite(getTexture(Texture.ARROWS_00_NAME), 32, 0, 32, 32, "arrowDown"))
			return false;
		
		if(!addSprite(getTexture(Texture.ARROWS_00_NAME), 64, 0, 32, 32, "arrowLeft"))
			return false;
		
		if(!addSprite(getTexture(Texture.ARROWS_00_NAME), 96, 0, 32, 32, "arrowUp"))
			return false;
		
		if(!addSprite(getTexture(Texture.DOORS_00_NAME), 0, 0, 64, 64, "doorClosed"))
			return false;
		
		if(!addSprite(getTexture(Texture.DOORS_00_NAME), 64, 0, 64, 64, "doorOpen"))
			return false;
			
		if(!createSpritesFromTexture(getTexture(Texture.LEVEL_TILES_00_NAME)))
			return false;
		
		return true;
		
	}

	public boolean addTexture(String fileName, String textureName) {

		File f = new File(fileName);
		if(f.exists() && !f.isDirectory()) {
			
			Logger.printDebug("Texture " + textureName + " added.");
			textures.put(textureName, new Texture(fileName));
			return true;
			
		} else {
			
			return false;
			
		}
		
	}

	public boolean addSprite(Texture texture, int x, int y, int width, int height,
			String spriteName) {

		if(texture != null) {
			
			Logger.printDebug("Sprite " + spriteName + " added.");
			sprites.put(spriteName, new Sprite(texture, x, y, width, height));
			return true;
			
		} else {
			
			Logger.printDebug("Attempting to create a sprite from a null texture.");
			return false;
			
		}
		
	}
	
	public boolean createSpritesFromTexture(Texture texture) {
		
		if(!addSprite(texture, 32, 0, 32, 32, Block.GROUND_00_NAME))
			return false;
		
		if(!addSprite(texture, 64, 0, 32, 32, Block.GROUND_01_NAME))
			return false;
		
		if(!addSprite(texture, 96, 0, 32, 32, Block.GROUND_02_NAME))
			return false;
		
		if(!addSprite(texture, 128, 0, 32, 32, Block.GROUND_03_NAME))
			return false;
		
		if(!addSprite(texture, 160, 0, 32, 32, Block.GROUND_04_NAME))
			return false;
		
		if(!addSprite(texture, 192, 0, 32, 32, Block.GROUND_05_NAME))
			return false;
		
		if(!addSprite(texture, 224, 0, 32, 32, Block.GROUND_06_NAME))
			return false;
		
		if(!addSprite(texture, 256, 0, 32, 32, Block.GROUND_07_NAME))
			return false;
		
		if(!addSprite(texture, 288, 0, 32, 32, Block.GROUND_08_NAME))
			return false;
		
		if(!addSprite(texture, 0, 32, 32, 32, Block.GROUND_09_NAME))
			return false;
		
		if(!addSprite(texture, 32, 32, 32, 32, Block.GROUND_10_NAME))
			return false;
		
		if(!addSprite(texture, 64, 32, 32, 32, Block.GROUND_11_NAME))
			return false;
		
		if(!addSprite(texture, 96, 32, 32, 32, Block.GROUND_12_NAME))
			return false;
		
		if(!addSprite(texture, 128, 32, 32, 32, Block.GROUND_13_NAME))
			return false;
		
		if(!addSprite(texture, 160, 32, 32, 32, Block.GROUND_14_NAME))
			return false;
		
		if(!addSprite(texture, 192, 32, 32, 32, Block.GROUND_15_NAME))
			return false;
		
		if(!addSprite(texture, 224, 32, 32, 32, Block.GROUND_16_NAME))
			return false;
		
		if(!addSprite(texture, 256, 32, 32, 32, Block.GROUND_17_NAME))
			return false;
		
		if(!addSprite(texture, 288, 32, 32, 32, Block.GROUND_18_NAME))
			return false;
		
		if(!addSprite(texture, 0, 64, 32, 32, Block.GROUND_19_NAME))
			return false;
		
		if(!addSprite(texture, 32, 64, 32, 32, Block.GROUND_20_NAME))
			return false;
		
		if(!addSprite(texture, 64, 64, 32, 32, Block.GROUND_21_NAME))
			return false;
		
		if(!addSprite(texture, 96, 64, 32, 32, Block.GROUND_22_NAME))
			return false;
		
		if(!addSprite(texture, 128, 64, 32, 32, Block.GROUND_23_NAME))
			return false;
		
		if(!addSprite(texture, 160, 64, 32, 32, Block.GROUND_24_NAME))
			return false;
		
		if(!addSprite(texture, 192, 64, 32, 32, Block.GROUND_25_NAME))
			return false;
		
		if(!addSprite(texture, 224, 64, 32, 32, Block.GROUND_26_NAME))
			return false;
		
		if(!addSprite(texture, 256, 64, 32, 32, Block.GROUND_27_NAME))
			return false;
		
		if(!addSprite(texture, 288, 64, 32, 32, Block.GROUND_28_NAME))
			return false;
		
		if(!addSprite(texture, 0, 96, 32, 32, Block.GROUND_29_NAME))
			return false;
		
		if(!addSprite(texture, 32, 96, 32, 32, Block.GROUND_30_NAME))
			return false;
		
		if(!addSprite(texture, 64, 96, 32, 32, Block.GROUND_31_NAME))
			return false;
		
		if(!addSprite(texture, 96, 96, 32, 32, Block.GROUND_32_NAME))
			return false;
		
		if(!addSprite(texture, 128, 96, 32, 32, Block.GROUND_33_NAME))
			return false;
		
		if(!addSprite(texture, 160, 96, 32, 32, Block.GROUND_34_NAME))
			return false;
		
		if(!addSprite(texture, 192, 96, 32, 32, Block.GROUND_35_NAME))
			return false;
		
		if(!addSprite(texture, 224, 96, 32, 32, Block.GROUND_36_NAME))
			return false;
		
		if(!addSprite(texture, 256, 96, 32, 32, Block.GROUND_37_NAME))
			return false;
		
		if(!addSprite(texture, 288, 96, 32, 32, Block.GROUND_38_NAME))
			return false;
		
		if(!addSprite(texture, 0, 128, 32, 32, Block.LADDER_00_NAME))
			return false;
		
		if(!addSprite(texture, 32, 128, 32, 32, Block.WOODSTRIP_00_NAME))
			return false;
		
		if(!addSprite(texture, 64, 128, 32, 32, Block.WOODSTRIP_01_NAME))
			return false;
		
		if(!addSprite(texture, 96, 128, 32, 16, Block.WOODSTRIP_02_NAME))
			return false;
		
		if(!addSprite(texture, 128, 144, 32, 16, Block.WOODSTRIP_03_NAME))
			return false;
		
		if(!addSprite(texture, 160, 128, 16, 32, Block.WOODSTRIP_04_NAME))
			return false;
		
		if(!addSprite(texture, 208, 128, 16, 32, Block.WOODSTRIP_05_NAME))
			return false;
		
		if(!addSprite(texture, 224, 128, 32, 32, Block.CRATE_00_NAME))
			return false;
		
		if(!addSprite(texture, 256, 128, 32, 32, Block.ARROWCRATE_00_NAME))
			return false;
		
		if(!addSprite(texture, 288, 128, 32, 32, Block.ARROWCRATE_01_NAME))
			return false;
		
		if(!addSprite(texture, 0, 160, 32, 32, Block.ARROWCRATE_02_NAME))
			return false;
		
		if(!addSprite(texture, 32, 160, 32, 32, Block.ARROWCRATE_03_NAME))
			return false;
		
		if(!addSprite(texture, 64, 169, 32, 23, Block.SPIKES_00_NAME))
			return false;
		
		if(!addSprite(texture, 96, 160, 23, 32, Block.SPIKES_01_NAME))
			return false;
		
		if(!addSprite(texture, 128, 160, 32, 23, Block.SPIKES_02_NAME))
			return false;
		
		if(!addSprite(texture, 169, 160, 23, 32, Block.SPIKES_03_NAME))
			return false;
		
		if(!addSprite(texture, 0, 192, 32, 32, Block.WATER_00_NAME))
			return false;
		
		if(!addSprite(texture, 32, 193, 32, 31, Block.WATER_01_NAME))
			return false;
		
		if(!addSprite(texture, 64, 192, 32, 32, Block.LAVA_00_NAME))
			return false;
		
		if(!addSprite(texture, 96, 193, 32, 31, Block.LAVA_01_NAME))
			return false;
		
		if(!addSprite(texture, 128, 192, 32, 32, Block.TORCH_00_NAME))
			return false;
		
		if(!addSprite(texture, 160, 192, 32, 32, Block.TORCH_01_NAME))
			return false;
		
		if(!addSprite(texture, 192, 160, 32, 32, Block.ARROWCRATE_04_NAME))
			return false;
		
		if(!addSprite(texture, 224, 160, 32, 32, Block.COIN_00_NAME))
			return false;
		
		return true;
		
	}

	public Texture getTexture(String textureName) {

		return textures.get(textureName);

	}

	public Sprite getSprite(String spriteName) {

		return sprites.get(spriteName);
		
	}

	
}