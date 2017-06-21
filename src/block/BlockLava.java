package block;

import client.Game;
import client.Logger;

public class BlockLava extends BlockLiquid {

	public BlockLava(int sub) {
		
		super(sub, 1);

		setDeadly(true);
		
		switch(sub) {
		
		case 0:
			sprite = Game.gm.getSprite(LAVA_00_NAME);
			break;
		case 1:
			sprite = Game.gm.getSprite(LAVA_01_NAME);
			setWidthHeightOffsets(32, 31, 0, 1);
			break;
		default:
			Logger.printDebug("No such lava sub block exists.");
			break;
		
		}
		
	}

}
