package block;

import client.Game;

public class BlockLadder extends Block {

	public BlockLadder(int sub) {
		
		super(sub, 0);
		
		sprite = Game.gm.getSprite(LADDER_00_NAME);
		
	}

}
