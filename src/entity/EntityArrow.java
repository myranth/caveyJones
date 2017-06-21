package entity;

import graphics.Sprite;

import java.awt.Graphics;

import maths.BoundingBox;
import maths.CollisionHelper;
import block.Block;
import block.BlockBreakable;
import block.BlockBreakableSpecial;
import block.BlockSolid;
import client.Game;
import client.Logger;

public class EntityArrow {

	public static final float speed = 3.7f;
	
	private int direction; // 0 = right, 1 = down, 2 = left, 3 = up
	private Sprite sprite;
	private float xPos;
	private float yPos;
	private BoundingBox bb;
	private boolean isBroken;
	
	public EntityArrow(float xPos, float yPos, int direction) {
		
		this.xPos = xPos;
		this.yPos = yPos;
		this.direction = direction;
		isBroken = false;
		
		switch(direction) {
		
		case 0:
			sprite = Game.gm.getSprite("arrowRight");
			bb = new BoundingBox(xPos + 5, yPos + 12, 22, 8);
			break;
		case 1:
			sprite = Game.gm.getSprite("arrowDown");
			bb = new BoundingBox(xPos + 12, yPos + 5, 8, 22);
			break;
		case 2:
			sprite = Game.gm.getSprite("arrowLeft");
			bb = new BoundingBox(xPos + 5, yPos + 12, 22, 8);
			break;
		case 3:
			sprite = Game.gm.getSprite("arrowUp");
			bb = new BoundingBox(xPos + 12, yPos + 5, 8, 22);
			break;
		
		}
		
	}
	
	public void tick(float dt) {
		
		breakSpecial();
		
		switch(direction) {
		
		case 0:
			xPos += speed * dt * 0.1;
			break;
		case 1:
			yPos += speed * dt * 0.1;
			break;
		case 2:
			xPos -= speed * dt * 0.1;
			break;
		case 3:
			yPos -= speed * dt * 0.1;
			break;
		
		}
		
		updateBoundingBox();
		
		if(isCollidingWithSolid()) isBroken = true;
		
	}
	
	private boolean isCollidingWithSolid() {
		
		Block[] nearbyBlocks = Game.lm.getCurrentLevel().getNearbyBlocks(xPos, yPos);
		
		for(int i = 0; i < nearbyBlocks.length; i++) {
			
			if(nearbyBlocks[i] != null && nearbyBlocks[i] instanceof BlockSolid) {
				
				BoundingBox currentBlockBB = nearbyBlocks[i].getBoundingBox();
				
				if(CollisionHelper.AABBColliding(this.bb, currentBlockBB)) {
					
					if(nearbyBlocks[i] instanceof BlockBreakable) {
						
						((BlockBreakable) nearbyBlocks[i]).setIsBreaking(true);
						
					}
					
					return true;
					
				}
				
			}
			
		}
		
		return false;
		
	}
	
	private void updateBoundingBox() {
		
		switch(direction) {
		
		case 0:
		case 2:
			bb.setX(xPos + 5);
			bb.setY(yPos + 12);
			break;
		case 1:
		case 3:
			bb.setX(xPos + 12);
			bb.setY(yPos + 5);
			break;
		
		}
		
	}
	
	public void render(Graphics g) {
		
		g.drawImage(sprite.getSprite(), (int)(xPos - Game.cam.getX()), (int)(yPos - Game.cam.getY()), null);
		
	}
	
	private void breakSpecial() {
		
		Block[] nearbyBlocks = Game.lm.getCurrentLevel().getNearbyBlocks(xPos, yPos);
		
		for(int i = 0; i < nearbyBlocks.length; i++) {
			
			if(nearbyBlocks[i] != null && nearbyBlocks[i] instanceof BlockBreakableSpecial) {
				
				BoundingBox currentBlockBB = nearbyBlocks[i].getBoundingBox();
				
				if(CollisionHelper.AABBColliding(this.bb, currentBlockBB)) {
						
					((BlockBreakableSpecial) nearbyBlocks[i]).setIsBreaking(true);
					Logger.printDebug("Breaking special block");
					//Game.lm.getCurrentLevel().updateWetness();
					
				}
				
			}
			
		}
		
	}
	
	public boolean isBroken() { return isBroken; }
	public BoundingBox getBB() { return bb; }
	
}