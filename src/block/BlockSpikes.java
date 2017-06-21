package block;

import client.Game;
import client.Logger;

public class BlockSpikes extends BlockBreakableSpecial {

	public BlockSpikes(int sub) {
		
		super(sub, 1);
		
		setDeadly(true);
		
		switch(sub) {
		
		case 0:
			setWidthHeightOffsets(32, 23, 0, 9);
			sprite = Game.gm.getSprite(SPIKES_00_NAME);
			break;
		case 1:
			setWidthHeightOffsets(23, 32, 0, 0);
			sprite = Game.gm.getSprite(SPIKES_01_NAME);
			break;
		case 2:
			setWidthHeightOffsets(32, 23, 0, 0);
			sprite = Game.gm.getSprite(SPIKES_02_NAME);
			break;
		case 3:
			setWidthHeightOffsets(23, 32, 9, 0);
			sprite = Game.gm.getSprite(SPIKES_03_NAME);
			break;
		default:
			Logger.printDebug("No such sub block exists.");
			break;
		
		}
		
	}

}
