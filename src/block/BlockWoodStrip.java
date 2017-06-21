package block;

import client.Game;
import client.Logger;

public class BlockWoodStrip extends BlockSolid {

	public BlockWoodStrip(int sub) {
		
		super(sub, 0);
		
		switch(sub) {
		
		case 0:
			sprite = Game.gm.getSprite(WOODSTRIP_00_NAME);
			break;
		case 1:
			sprite = Game.gm.getSprite(WOODSTRIP_01_NAME);
			break;
		case 2:
			setWidthHeightOffsets(32, 16, 0, 0);
			sprite = Game.gm.getSprite(WOODSTRIP_02_NAME);
			break;
		case 3:
			setWidthHeightOffsets(32, 16, 0, 16);
			sprite = Game.gm.getSprite(WOODSTRIP_03_NAME);
			break;
		case 4:
			setWidthHeightOffsets(16, 32, 0, 0);
			sprite = Game.gm.getSprite(WOODSTRIP_04_NAME);
			break;
		case 5:
			setWidthHeightOffsets(16, 32, 16, 0);
			sprite = Game.gm.getSprite(WOODSTRIP_05_NAME);
			break;
		default:
			Logger.printDebug("Sub block does not exist.");
			break;
		
		}
		
	}
	
}