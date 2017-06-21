package block;

import client.Game;
import client.Logger;

public class BlockWater extends BlockLiquid {

	public BlockWater(int sub) {
		
		super(sub, 1);
		
		switch(sub) {
		
		case 0:
			sprite = Game.gm.getSprite(WATER_00_NAME);
			break;
		case 1:
			sprite = Game.gm.getSprite(WATER_01_NAME);
			setWidthHeightOffsets(32, 31, 0, 1);
			break;
		default:
			Logger.printDebug("No such water sub block exists.");
		
		}
		
	}

}