package block;

import graphics.Texture;

import java.awt.Graphics;

import client.Game;

public class BlockTorch extends Block {

	public BlockTorch(int sub) {
		
		super(sub, 2);
		
		switch(sub) {
		
		case 0:
			sprite = Game.gm.getSprite(TORCH_00_NAME);
			break;
		case 1:
			sprite = Game.gm.getSprite(TORCH_01_NAME);
			break;
		
		}
		
	}
	
	@Override
	public void render(Graphics g) {
		
		if(sprite != null && Game.cam != null) {
			
			g.drawImage(sprite.getSprite(), xPos + xOffset - (int)Game.cam.getX(), yPos + yOffset - (int)Game.cam.getY(), null);
			g.drawImage(Game.gm.getTexture(Texture.GLOW_00_NAME).getImage(), xPos - 140 - (int)Game.cam.getX(), yPos - 152 - (int)Game.cam.getY(), null);
			
		}
			
	}

}
