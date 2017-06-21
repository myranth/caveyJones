package block;

import java.awt.Graphics;

import client.Game;
import client.Logger;

public class BlockArrowCrate extends BlockCrate {

	private boolean isWet;
	
	public BlockArrowCrate(int sub) {
		
		super(sub);
		isWet = false;
		
		switch(sub) {
		
		case 0:
			sprite = Game.gm.getSprite(ARROWCRATE_00_NAME);
			break;
		case 1:
			sprite = Game.gm.getSprite(ARROWCRATE_01_NAME);
			break;
		case 2:
			sprite = Game.gm.getSprite(ARROWCRATE_02_NAME);
			break;
		case 3:
			sprite = Game.gm.getSprite(ARROWCRATE_03_NAME);
			break;
		case 4:
			sprite = Game.gm.getSprite(ARROWCRATE_04_NAME);
			break;
		default:
			Logger.printDebug("No such arrow create sub block exists.");
			break;
		
		}
		
	}
	
	public void render(Graphics g) {
		
		renderSprite(g);
		
		if(isWet) {
			
			g.drawImage(Game.gm.getSprite(Block.WATER_00_NAME).getSprite(),
					xPos - (int)Game.cam.getX(), yPos - (int)Game.cam.getY(), null);
			
		}
		
	}
	
	public void setIsWet(boolean b) { isWet = b; }

}
