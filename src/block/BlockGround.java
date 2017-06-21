package block;

import client.Game;
import client.Logger;

public class BlockGround extends BlockSolid {

	public BlockGround(int sub) {
		
		super(sub, 0);
		
		switch(sub) {
		
		case 0:
			sprite = Game.gm.getSprite(GROUND_00_NAME);
			break;
		case 1:
			sprite = Game.gm.getSprite(GROUND_01_NAME);
			break;
		case 2:
			sprite = Game.gm.getSprite(GROUND_02_NAME);
			break;
		case 3:
			sprite = Game.gm.getSprite(GROUND_03_NAME);
			break;
		case 4:
			sprite = Game.gm.getSprite(GROUND_04_NAME);
			break;
		case 5:
			sprite = Game.gm.getSprite(GROUND_05_NAME);
			break;
		case 6:
			sprite = Game.gm.getSprite(GROUND_06_NAME);
			break;
		case 7:
			sprite = Game.gm.getSprite(GROUND_07_NAME);
			break;
		case 8:
			sprite = Game.gm.getSprite(GROUND_08_NAME);
			break;
		case 9:
			sprite = Game.gm.getSprite(GROUND_09_NAME);
			break;
		case 10:
			sprite = Game.gm.getSprite(GROUND_10_NAME);
			break;
		case 11:
			sprite = Game.gm.getSprite(GROUND_11_NAME);
			break;
		case 12:
			sprite = Game.gm.getSprite(GROUND_12_NAME);
			break;
		case 13:
			sprite = Game.gm.getSprite(GROUND_13_NAME);
			break;
		case 14:
			sprite = Game.gm.getSprite(GROUND_14_NAME);
			break;
		case 15:
			sprite = Game.gm.getSprite(GROUND_15_NAME);
			break;
		case 16:
			sprite = Game.gm.getSprite(GROUND_16_NAME);
			break;
		case 17:
			sprite = Game.gm.getSprite(GROUND_17_NAME);
			break;
		case 18:
			sprite = Game.gm.getSprite(GROUND_18_NAME);
			break;
		case 19:
			sprite = Game.gm.getSprite(GROUND_19_NAME);
			break;
		case 20:
			sprite = Game.gm.getSprite(GROUND_20_NAME);
			break;
		case 21:
			sprite = Game.gm.getSprite(GROUND_21_NAME);
			break;
		case 22:
			sprite = Game.gm.getSprite(GROUND_22_NAME);
			break;
		case 23:
			sprite = Game.gm.getSprite(GROUND_23_NAME);
			break;
		case 24:
			sprite = Game.gm.getSprite(GROUND_24_NAME);
			break;
		case 25:
			sprite = Game.gm.getSprite(GROUND_25_NAME);
			break;
		case 26:
			sprite = Game.gm.getSprite(GROUND_26_NAME);
			break;
		case 27:
			sprite = Game.gm.getSprite(GROUND_27_NAME);
			break;
		case 28:
			sprite = Game.gm.getSprite(GROUND_28_NAME);
			break;
		case 29:
			sprite = Game.gm.getSprite(GROUND_29_NAME);
			break;
		case 30:
			sprite = Game.gm.getSprite(GROUND_30_NAME);
			break;
		case 31:
			sprite = Game.gm.getSprite(GROUND_31_NAME);
			break;
		case 32:
			sprite = Game.gm.getSprite(GROUND_32_NAME);
			break;
		case 33:
			sprite = Game.gm.getSprite(GROUND_33_NAME);
			break;
		case 34:
			sprite = Game.gm.getSprite(GROUND_34_NAME);
			break;
		case 35:
			sprite = Game.gm.getSprite(GROUND_35_NAME);
			break;
		case 36:
			sprite = Game.gm.getSprite(GROUND_36_NAME);
			break;
		case 37:
			sprite = Game.gm.getSprite(GROUND_37_NAME);
			break;
		case 38:
			sprite = Game.gm.getSprite(GROUND_38_NAME);
			break;
			
		default:
			Logger.printDebug("No such sub block exists.");
			break;
		
		}
		
	}

}