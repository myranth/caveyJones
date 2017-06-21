package level;

import java.awt.Graphics;

import block.Block;
import block.BlockArrowCrate;
import block.BlockBreakable;
import block.BlockBreakableSpecial;
import block.BlockCoin;
import block.BlockSpawn;
import block.BlockSpikes;
import block.BlockWater;
import client.Game;
import client.Logger;

public class Level {
	
	public static final String LEVEL_00_FILE = "res/levels/level0.txt";
	public static final String LEVEL_01_FILE = "res/levels/level1.txt";
	public static final String LEVEL_02_FILE = "res/levels/level2.txt";
	public static final String LEVEL_03_FILE = "res/levels/level3.txt";
	public static final String LEVEL_04_FILE = "res/levels/level4.txt";	
	public static final String LEVEL_05_FILE = "res/levels/level5.txt";
	public static final String LEVEL_06_FILE = "res/levels/level6.txt";
	public static final String LEVEL_07_FILE = "res/levels/level7.txt";
	public static final String LEVEL_08_FILE = "res/levels/level8.txt";
	public static final String LEVEL_09_FILE = "res/levels/level9.txt";
	public static final String LEVEL_10_FILE = "res/levels/level10.txt";
	public static final String LEVEL_11_FILE = "res/levels/level11.txt";
	public static final String LEVEL_12_FILE = "res/levels/level12.txt";
	public static final String LEVEL_13_FILE = "res/levels/level13.txt";
	public static final String LEVEL_14_FILE = "res/levels/level14.txt";
	public static final String LEVEL_FLYINGARROWS_FILE = "res/levels/flyingArrows.txt";
	
	public static final String LEVEL_00_NAME = "level00";
	public static final String LEVEL_01_NAME = "level01";
	public static final String LEVEL_02_NAME = "level02";
	public static final String LEVEL_03_NAME = "level03";
	public static final String LEVEL_04_NAME = "level04";
	public static final String LEVEL_05_NAME = "level05";
	public static final String LEVEL_06_NAME = "level06";
	public static final String LEVEL_07_NAME = "level07";
	public static final String LEVEL_08_NAME = "level08";
	public static final String LEVEL_09_NAME = "level09";
	public static final String LEVEL_10_NAME = "level10";
	public static final String LEVEL_11_NAME = "level11";
	public static final String LEVEL_12_NAME = "level12";
	public static final String LEVEL_13_NAME = "level13";
	public static final String LEVEL_14_NAME = "level14";
	public static final String LEVEL_FLYINGARROWS_NAME = "levelFlyingArrows";
	
	private int width;
	private int height;
	private int tileWidth;
	private int tileHeight;
	private String tileSet;
	private int[][] levelMap;
	private Block[][] blockMap;
	private int coins;
	
	public Level(int width, int height, int tileWidth, int tileHeight, String tileSet) {
		
		Logger.printDebug(width + " " + height + " " + tileWidth + " " + tileHeight + " " + tileSet);
		this.width = width;
		this.height = height;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.tileSet = tileSet;
		this.levelMap = new int[width][height];
		coins = 0;
		
	}
	
	public void initBlockMap() {
		
		Logger.printDebug("Initializing block map.");
		blockMap = new Block[width][height];

		for(int x = 0; x < width; x++) {
			
			for(int y = 0; y < height; y++) {
				
				blockMap[x][y] = Block.getBlockFromId(levelMap[x][y]);
				
				if(Block.getBlockFromId(levelMap[x][y]) instanceof BlockCoin) coins++;
				
				if(blockMap[x][y] != null)
					blockMap[x][y].setCoordinates(x * 32, y * 32);
				
			}
			
		}
		
		updateWetness();
		
	}
	
	public void setBlock(int x, int y, Block b) {
		
		if(blocksLoaded()) blockMap[x][y] = b;
		
	}
	
	public boolean blocksLoaded() {
		
		return blockMap != null;
		
	}
	
	public void render(Graphics g, int renderPass) {
		
		for(int x = 0; x < width; x++) {
			
			for(int y = 0; y < height; y++) {
				
				if(blocksLoaded()) {
					
					if(blockMap[x][y] != null) {
						
						if(blockMap[x][y].getRenderPass() == renderPass) blockMap[x][y].render(g);
						
					}	
					
				}
				
			}
			
		}
		
	}
	
	public void printLevel() {
		
		for(int y = 0; y < height; y++) {
			
			for(int x = 0; x < width; x++) {
				
				System.out.print(levelMap[x][y] + " ");
				
			}
			
			System.out.println();
			
		}
		
	}
	
	public Block[] getNearbyBlocks(float xPos, float yPos) {
		
		int x = (int)(xPos / tileWidth);
		int y = (int)(yPos / tileHeight);
		
		Block[] blocks = new Block[25];
		int b = 0;
		
		for(int i = -2; i <= 2; i++) {
			
			for(int j = -2; j <= 2; j++) {
				
				if((x + i) >= 0 && (x + i) < width && (y + j) >= 0 && (y + j) < height)
					blocks[b] = blockMap[x + i][y + j];
				
				b++;
				
			}
			
		}
		
		return blocks;
		
	}
	
	public void reset() {
		
		initBlockMap();
		Game.cavey.setXPos(Game.lm.getCurrentLevel().getSpawnX());
		Game.cavey.setYPos(Game.lm.getCurrentLevel().getSpawnY());
		Game.cam.setMaxHeight(Game.lm.getCurrentLevel().getHeight() * 32);
		Game.cam.setMaxWidth(Game.lm.getCurrentLevel().getWidth() * 32);
		
	}
	
	@SuppressWarnings("unused")
	private void countCoins() {
		
		for(int i = 0; i < width; i++) {
			
			for(int j = 0; i < height; j++) {
				
				if(blockMap[i][j] instanceof BlockCoin) coins++;
				
			}
			
		}
		
	}
	
	// Yes, really, this is a method
	public void updateWetness() {
		
		for(int i = 0; i < width; i++) {
			
			for(int j = 0; j < height; j++) {
				
				if(blockMap[i][j] instanceof BlockSpikes) {
					
					int adjacentWater = 0;
					int diagonalWater = 0;
					if(i+1 >= 0 && i + 1 < width) if(blockMap[i + 1][j] instanceof BlockWater && blockMap[i + 1][j].getSub() == 0) adjacentWater++;
					if(i-1 >= 0 && i - 1 < width) if(blockMap[i - 1][j] instanceof BlockWater && blockMap[i - 1][j].getSub() == 0) adjacentWater++;
					if(j+1 >= 0 && j + 1 < height) if(blockMap[i][j + 1] instanceof BlockWater && blockMap[i][j + 1].getSub() == 0) adjacentWater++;
					if(j-1 >= 0 && j - 1 < height) if(blockMap[i][j - 1] instanceof BlockWater && blockMap[i][j - 1].getSub() == 0) adjacentWater++;
					if(i + 1 < width && i + 1 >= 0 && j + 1 >=0 && j + 1 < height) if(blockMap[i + 1][j + 1] instanceof BlockWater && blockMap[i + 1][j + 1].getSub() == 0) diagonalWater++;
					if(i + 1 < width && i + 1 >= 0 && j - 1 >=0 && j - 1 < height) if(blockMap[i + 1][j - 1] instanceof BlockWater && blockMap[i + 1][j - 1].getSub() == 0) diagonalWater++;
					if(i - 1 < width && i - 1 >= 0 && j + 1 >=0 && j + 1 < height) if(blockMap[i - 1][j + 1] instanceof BlockWater && blockMap[i - 1][j + 1].getSub() == 0) diagonalWater++;
					if(i - 1 < width && i - 1 >= 0 && j - 1 >=0 && j - 1 < height) if(blockMap[i - 1][j - 1] instanceof BlockWater && blockMap[i - 1][j - 1].getSub() == 0) diagonalWater++;
					
					if(adjacentWater > 0 || diagonalWater > 1) ((BlockSpikes)blockMap[i][j]).setIsWet(true);
					
				}
				
				if(blockMap[i][j] instanceof BlockCoin) {
					
					int adjacentWater = 0;
					if(i+1 >= 0 && i + 1 < width) if(blockMap[i + 1][j] instanceof BlockWater) adjacentWater++;
					if(i-1 >= 0 && i - 1 < width) if(blockMap[i - 1][j] instanceof BlockWater) adjacentWater++;
					if(j+1 >= 0 && j + 1 < height) if(blockMap[i][j + 1] instanceof BlockWater) adjacentWater++;
					if(j-1 >= 0 && j - 1 < height) if(blockMap[i][j - 1] instanceof BlockWater) adjacentWater++;

					if(adjacentWater > 1) ((BlockCoin)blockMap[i][j]).setIsWet(true);
					
				}
				
				if(blockMap[i][j] instanceof BlockArrowCrate) {
					
					int adjacentWater = 0;
					if(i+1 >= 0 && i + 1 < width) if(blockMap[i + 1][j] instanceof BlockWater) adjacentWater++;
					if(i-1 >= 0 && i - 1 < width) if(blockMap[i - 1][j] instanceof BlockWater) adjacentWater++;
					if(j+1 >= 0 && j + 1 < height) if(blockMap[i][j + 1] instanceof BlockWater) adjacentWater++;
					if(j-1 >= 0 && j - 1 < height) if(blockMap[i][j - 1] instanceof BlockWater) adjacentWater++;

					if(adjacentWater > 1) ((BlockArrowCrate)blockMap[i][j]).setIsWet(true);
					
				}
				
			}
			
		}
		
	}
	
	public void tickLevel() {
		
		coins = 0;
		
		for(int i = 0; i < width; i++) {
			
			for(int j = 0; j < height; j++) {
				
				if(blockMap[i][j] instanceof BlockCoin) coins++;
				
				if(blockMap[i][j] == null) {
					
					int adjacentWater = 0;
					if(i+1 >= 0 && i + 1 < width) if(blockMap[i + 1][j] instanceof BlockWater) adjacentWater++;
					if(i-1 >= 0 && i - 1 < width) if(blockMap[i - 1][j] instanceof BlockWater) adjacentWater++;
					if(j+1 >= 0 && j + 1 < height) if(blockMap[i][j + 1] instanceof BlockWater) adjacentWater++;
					if(j-1 >= 0 && j - 1 < height) if(blockMap[i][j - 1] instanceof BlockWater) adjacentWater++;

					if(adjacentWater > 1) {
						
						//updateWetness();
						blockMap[i][j] = new BlockWater(0);
						blockMap[i][j].setCoordinates(i * 32, j * 32);
						Logger.printDebug("New water square @ " + blockMap[i][j].getX() + ", " + blockMap[i][j].getY());
					
					}
					
				}
				
				if(blockMap[i][j] instanceof BlockBreakable) {
					
					((BlockBreakable) blockMap[i][j]).tick();
					
					if(((BlockBreakable) blockMap[i][j]).isBroken()) {
						
						if(blockMap[i][j] instanceof BlockArrowCrate) {
						
							int x = blockMap[i][j].getX();
							int y = blockMap[i][j].getY();
							
							switch(blockMap[i][j].getSub()) {
							
							case 0:
								Game.am.add(x, y, 0);
								break;
							case 1:
								Game.am.add(x, y, 1);
								break;
							case 2:
								Game.am.add(x, y, 2);
								break;
							case 3:
								Game.am.add(x, y, 3);
								break;
							case 4:
								Game.am.add(x, y, 0);
								Game.am.add(x, y, 1);
								Game.am.add(x, y, 2);
								Game.am.add(x, y, 3);
								break;
							
							}
							
						}
						
						blockMap[i][j] = null;
						
					}
					
				}
				
				if(blockMap[i][j] instanceof BlockBreakableSpecial) {
					
					((BlockBreakableSpecial) blockMap[i][j]).tick();
					
					if(((BlockBreakableSpecial) blockMap[i][j]).isBroken()) {
						
						blockMap[i][j] = null;
						
					}
					
				}
				
			}
			
		}
		
	}
	
	/* SETS */
	public void setTile(int x, int y, int i) { levelMap[x][y] = i; }
	
	/* GETS */
	public Block getBlock(int x, int y) { return blockMap[x][y]; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getTileWidth() { return tileWidth; }
	public int getTileHeight() { return tileHeight; }
	public String getTileSet() { return tileSet; }
	public int getCoins() { return coins; }
	
	public int getSpawnX() {
		
		for(int i = 0; i < width; i++) {
			
			for(int j = 0; j < height; j++) {
				
				if(blockMap[i][j] instanceof BlockSpawn) return blockMap[i][j].getX();
				
			}
			
		}
		
		return 1;
		
	}
	
	public int getSpawnY() {
		
		for(int i = 0; i < width; i++) {
			
			for(int j = 0; j < height; j++) {
				
				if(blockMap[i][j] instanceof BlockSpawn) return blockMap[i][j].getY();
				
			}
			
		}
		
		return 1;
		
	}
	
}
