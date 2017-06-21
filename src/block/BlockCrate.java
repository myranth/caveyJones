package block;

import client.Game;

public class BlockCrate extends BlockBreakable {
	
	public BlockCrate(int sub) {
		
		super(sub, 0);
		
		// Explosion and arrow crates are subclasses of this
		sprite = Game.gm.getSprite(CRATE_00_NAME);
		
	}

}