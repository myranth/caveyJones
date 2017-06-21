package block;

import java.awt.Graphics;

import client.Game;

public class BlockCoin extends Block {

	private boolean isWet;
	
	public BlockCoin() {
		
		super(0, 0);
		sprite = Game.gm.getSprite(COIN_00_NAME);
		isWet = false;
		
	}
	
	public void render(Graphics g) {
		
		renderSprite(g);
		
		if(isWet) {
			
			g.drawImage(Game.gm.getSprite(Block.WATER_00_NAME).getSprite(),
					xPos - (int)Game.cam.getX(), yPos - (int)Game.cam.getY(), null);
			
		}
		
	}
	
	public void setIsWet(boolean b){ isWet = b; }

}