package block;

import graphics.Sprite;

import java.awt.Graphics;
import client.Game;
import maths.BoundingBox;

public class Block {
	
	/* BLOCK NAMES */
	public static final String GROUND_00_NAME = "blockGround00";
	public static final String GROUND_01_NAME = "blockGround01";
	public static final String GROUND_02_NAME = "blockGround02";
	public static final String GROUND_03_NAME = "blockGround03";
	public static final String GROUND_04_NAME = "blockGround04";
	public static final String GROUND_05_NAME = "blockGround05";
	public static final String GROUND_06_NAME = "blockGround06";
	public static final String GROUND_07_NAME = "blockGround07";
	public static final String GROUND_08_NAME = "blockGround08";
	public static final String GROUND_09_NAME = "blockGround09";
	public static final String GROUND_10_NAME = "blockGround10";
	public static final String GROUND_11_NAME = "blockGround11";
	public static final String GROUND_12_NAME = "blockGround12";
	public static final String GROUND_13_NAME = "blockGround13";
	public static final String GROUND_14_NAME = "blockGround14";
	public static final String GROUND_15_NAME = "blockGround15";
	public static final String GROUND_16_NAME = "blockGround16";
	public static final String GROUND_17_NAME = "blockGround17";
	public static final String GROUND_18_NAME = "blockGround18";
	public static final String GROUND_19_NAME = "blockGround19";
	public static final String GROUND_20_NAME = "blockGround20";
	public static final String GROUND_21_NAME = "blockGround21";
	public static final String GROUND_22_NAME = "blockGround22";
	public static final String GROUND_23_NAME = "blockGround23";
	public static final String GROUND_24_NAME = "blockGround24";
	public static final String GROUND_25_NAME = "blockGround25";
	public static final String GROUND_26_NAME = "blockGround26";
	public static final String GROUND_27_NAME = "blockGround27";
	public static final String GROUND_28_NAME = "blockGround28";
	public static final String GROUND_29_NAME = "blockGround29";
	public static final String GROUND_30_NAME = "blockGround30";
	public static final String GROUND_31_NAME = "blockGround31";
	public static final String GROUND_32_NAME = "blockGround32";
	public static final String GROUND_33_NAME = "blockGround33";
	public static final String GROUND_34_NAME = "blockGround34";
	public static final String GROUND_35_NAME = "blockGround35";
	public static final String GROUND_36_NAME = "blockGround36";
	public static final String GROUND_37_NAME = "blockGround37";
	public static final String GROUND_38_NAME = "blockGround38";
	public static final String LADDER_00_NAME = "blockLadder";
	public static final String WOODSTRIP_00_NAME = "blockWoodStrip00";
	public static final String WOODSTRIP_01_NAME = "blockWoodStrip01";
	public static final String WOODSTRIP_02_NAME = "blockWoodStrip02";
	public static final String WOODSTRIP_03_NAME = "blockWoodStrip03";
	public static final String WOODSTRIP_04_NAME = "blockWoodStrip04";
	public static final String WOODSTRIP_05_NAME = "blockWoodStrip05";
	public static final String CRATE_00_NAME = "blockCrate00";
	public static final String ARROWCRATE_00_NAME = "blockArrowCrate00";
	public static final String ARROWCRATE_01_NAME = "blockArrowCrate01";
	public static final String ARROWCRATE_02_NAME = "blockArrowCrate02";
	public static final String ARROWCRATE_03_NAME = "blockArrowCrate03";
	public static final String ARROWCRATE_04_NAME = "blockArrowCrate04";
	public static final String SPIKES_00_NAME = "blockSpikes00";
	public static final String SPIKES_01_NAME = "blockSpikes01";
	public static final String SPIKES_02_NAME = "blockSpikes02";
	public static final String SPIKES_03_NAME = "blockSpikes03";
	public static final String WATER_00_NAME = "blockWater00";
	public static final String WATER_01_NAME = "blockWater01";
	public static final String LAVA_00_NAME = "blockLava00";
	public static final String LAVA_01_NAME = "blockLava01";
	public static final String SPAWN_00_NAME = "blockSpawn00";
	public static final String TORCH_00_NAME = "blockTorch00";
	public static final String TORCH_01_NAME = "blockTorch01";
	public static final String COIN_00_NAME = "blockCoin";
	
	protected int xPos;
	protected int yPos;
	protected int width;
	protected int height;
	protected int xOffset;
	protected int yOffset;
	private int sub;
	private int renderPass; // 0 = behind player, 1 = in front of player, 2 = really really in front
	protected BoundingBox bb;
	private boolean deadly;
	protected Sprite sprite;
	
	public Block(int sub, int renderPass) {
		
		this.sub = sub;
		this.renderPass = renderPass;
		setCoordinates(-1, -1);
		setWidthHeightOffsets(32, 32, 0, 0);
		deadly = false;
		
	}
	
	public void render(Graphics g) {
		
		renderSprite(g);
	}
	
	protected void renderSprite(Graphics g) {
		
		if(sprite != null && Game.cam != null)
			g.drawImage(sprite.getSprite(), xPos + xOffset - (int)Game.cam.getX(), yPos + yOffset - (int)Game.cam.getY(), null);
				
	}
	
	public void setCoordinates(int x, int y) {
		
		this.xPos = x;
		this.yPos = y;
		setBoundingBox();
		
	}
	
	public void setWidthHeightOffsets(int width, int height, int xOffset, int yOffset) {
		
		this.width = width;
		this.height = height;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		setBoundingBox();
		
	}
	
	public void setBoundingBox() {

		this.bb = new BoundingBox(xPos + xOffset, yPos + yOffset, width, height);

	}
	
	public BoundingBox getBoundingBox() { return this.bb; }
	
	public void setDeadly(boolean b) { deadly = b; }
	
	public boolean isDeadly() { return deadly; }
	public int getRenderPass() { return renderPass; }
	public int getX() { return xPos; }
	public int getY() { return yPos; }
	public int getSub() { return sub; }
	
	public static Block getBlockFromId(int id) {
		
		// This was a mistake - I thought flare files started at index 0 
		//for the sprites, but they start at 1
		switch(id - 1) {
		
		case 1:
			return new BlockGround(0);
		case 2:
			return new BlockGround(1);
		case 3: 
			return new BlockGround(2);
		case 4:
			return new BlockGround(3);
		case 5:
			return new BlockGround(4);
		case 6: 
			return new BlockGround(5);
		case 7:
			return new BlockGround(6);
		case 8:
			return new BlockGround(7);
		case 9: 
			return new BlockGround(8);
		case 10:
			return new BlockGround(9);
		case 11:
			return new BlockGround(10);
		case 12: 
			return new BlockGround(11);
		case 13:
			return new BlockGround(12);
		case 14:
			return new BlockGround(13);
		case 15: 
			return new BlockGround(14);
		case 16:
			return new BlockGround(15);
		case 17:
			return new BlockGround(16);
		case 18: 
			return new BlockGround(17);
		case 19:
			return new BlockGround(18);
		case 20:
			return new BlockGround(19);
		case 21: 
			return new BlockGround(20);
		case 22:
			return new BlockGround(21);
		case 23:
			return new BlockGround(22);
		case 24: 
			return new BlockGround(23);
		case 25:
			return new BlockGround(24);
		case 26: 
			return new BlockGround(25);
		case 27:
			return new BlockGround(26);
		case 28:
			return new BlockGround(27);
		case 29: 
			return new BlockGround(28);
		case 30:
			return new BlockGround(29);
		case 31:
			return new BlockGround(30);
		case 32: 
			return new BlockGround(31);
		case 33: 
			return new BlockGround(32);
		case 34: 
			return new BlockGround(33);
		case 35: 
			return new BlockGround(34);
		case 36: 
			return new BlockGround(35);
		case 37: 
			return new BlockGround(36);
		case 38: 
			return new BlockGround(37);
		case 40:
			return new BlockLadder(0);
		case 41:
			return new BlockWoodStrip(0);
		case 42: 
			return new BlockWoodStrip(1);
		case 43:
			return new BlockWoodStrip(2);
		case 44:
			return new BlockWoodStrip(3);
		case 45: 
			return new BlockWoodStrip(4);
		case 46:
			return new BlockWoodStrip(5);
		case 47:
			return new BlockCrate(0);
		case 48: 
			return new BlockArrowCrate(0);
		case 49:
			return new BlockArrowCrate(1);
		case 50:
			return new BlockArrowCrate(2);
		case 51:
			return new BlockArrowCrate(3);
		case 52:
			return new BlockSpikes(0);
		case 53:
			return new BlockSpikes(1);
		case 54:
			return new BlockSpikes(2);
		case 55:
			return new BlockSpikes(3);
		case 56:
			return new BlockArrowCrate(4);
		case 57:
			return new BlockCoin();
		case 60:
			return new BlockWater(0);
		case 61:
			return new BlockWater(1);
		case 62:
			return new BlockLava(0);
		case 63:
			return new BlockLava(1);
		case 64:
			return new BlockTorch(0);
		case 65:
			return new BlockTorch(1);
		case 98:
			return new BlockDoor();
		case 99:
			return new BlockSpawn();
		default:
			return null;
			
		}
		
	}
	
}
