package block;

import java.awt.Graphics;

import client.Game;

public class BlockDoor extends Block {

	public BlockDoor() {
		
		super(0, 0);
		this.sprite = Game.gm.getSprite("doorClosed");
		//setWidthHeightOffsets(32, 32, 16, 16);

	}
	
	public void render(Graphics g) {
		
		if(Game.lm.getCurrentLevel().getCoins() == 0) {
			
			this.sprite = Game.gm.getSprite("doorOpen");
			
		} else this.sprite = Game.gm.getSprite("doorClosed");
		
		renderSprite(g);
		
	}
	
}