package block;

import graphics.Texture;

import java.awt.Graphics;

import client.Game;

public class BlockBreakable extends BlockSolid {

	private boolean isBreaking;
	private boolean isBroken;
	private int breakIndex;
	
	public BlockBreakable(int sub, int renderPass) {
		
		super(sub, renderPass);
		
		isBreaking = false;
		isBroken = false;
		breakIndex = 0;
		
	}
	
	public void tick() {
		
		if(isBreaking && breakIndex < 9) breakIndex++;
		if(breakIndex == 9) isBroken = true;
		
	}
	
	public void setIsBreaking(boolean b) { isBreaking = b; }
	
	public boolean isBroken() { return isBroken; }
	
	@Override
	public void render(Graphics g) {
		
		renderSprite(g);
		
		if(isBreaking) {
			
			g.drawImage(Game.gm.getTexture(Texture.BREAK_00_NAME).getImage().getSubimage(320 - 32 - (breakIndex * 32), 0, 32, 32),
					xPos - (int)Game.cam.getX(), yPos - (int)Game.cam.getY(), null);
			
		}
		
	}

}
