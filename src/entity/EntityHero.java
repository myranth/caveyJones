package entity;

import graphics.Sprite;
import graphics.Texture;

import java.awt.Graphics;

import maths.BoundingBox;
import maths.CollisionHelper;
import block.Block;
import block.BlockBreakable;
import block.BlockCoin;
import block.BlockDoor;
import block.BlockLadder;
import block.BlockLiquid;
import block.BlockSolid;
import client.Game;
import client.SceneManager;

public class EntityHero {

	private float xPos;
	private float yPos;
	private float xVel = 0;
	private float yVel = 0;
	
	private float xAccMax;
	private float yAccMax;
	private float xVelMax;
	private float yVelMax;
	
	private float xAccLimit;
	private float yAccLimit;
	private float xVelLimit;
	private float yVelLimit;
	
	private boolean isDead = false;
	private boolean moveRight = false;
	private boolean moveLeft = false;
	private boolean moveUp = false;
	private boolean moveDown = false;
	
	private BoundingBox bb;
	protected Texture texture;
	protected Sprite sprite;
	
	public EntityHero(float xPos, float yPos, float xAccMax, float yAccMax, float xVelMax, float yVelMax, BoundingBox bb) {
		
		this.xPos = xPos;
		this.yPos = yPos;
		this.xAccMax = xAccMax;
		this.yAccMax = yAccMax;
		this.xVelMax = xVelMax;
		this.yVelMax = yVelMax;
		this.xAccLimit = xAccMax;
		this.yAccLimit = yAccMax;
		this.xVelLimit = xVelMax;
		this.yVelLimit = yVelMax;
		this.bb = bb;
		
	}
	
	public void render(Graphics g) {
		
		g.drawImage(sprite.getSprite(), (int)(xPos - Game.cam.getX()), (int)(yPos - Game.cam.getY()), null);
		//System.out.println("Rendering at " + (xPos - Game.cam.getX()) + ", " + (yPos - Game.cam.getY()));
		
	}
	
	public void updateBoundingBox() {
		
		bb.setX(xPos);
		bb.setY(yPos);
		
	}
	
	public void tick2(float deltaTime) {
		
		handleBlockBreaks();
		
		if(isCollidingWithDoor() && Game.lm.getCurrentLevel().getCoins() == 0) {
			
			Game.sm.setScene(SceneManager.Scene.LEVELCOMPLETE);
			
		}
		
		if(isCollidingWithArrow()) {
			
			isDead = true;
			Game.sm.setScene(SceneManager.Scene.LEVELDEAD);
			
		}
		
		if(isCollidingWithDeath()) {

			isDead = true;
			moveUp = false;
			moveLeft = false;
			moveRight = false;
			moveDown = false;
			Game.sm.setScene(SceneManager.Scene.LEVELDEAD);
			
		}
		
		if(isCollidingWithLiquid()) {
			
			setSpeedLiquid();
			
			if(!isDead) {
			
				if(moveUp) {
					
					yVel -= yAccLimit * deltaTime * 0.01f;
					
				} else {
					
					yVel += yAccLimit * deltaTime * 0.01f;
					
				}
			
			}
			
			if(yVel > yVelLimit) yVel = yVelLimit;
			if(yVel < -yVelLimit) yVel = -yVelLimit;
			yPos += yVel;
			
			updateBoundingBox();
			
			if(isCollidingWithSolid()) {
				
				yPos -= yVel;
				yVel = 0;
				
				updateBoundingBox();
				
			}
			
		} else if(isCollidingWithLadder()) {
			
			setSpeedLadder();
			
			if(!isDead) {
			
				if(moveUp) {
					
					yVel -= yAccLimit * deltaTime * 0.01f;
					
				} else if(moveDown) {
					
					yVel += yAccLimit * deltaTime * 0.01f;
					
				} else {
					
					yVel = 0;
					
				}
			
			}
			
			if(yVel > yVelLimit) yVel = yVelLimit;
			if(yVel < -yVelLimit) yVel = -yVelLimit;
			yPos += yVel;
			
			updateBoundingBox();
			
			if(isCollidingWithSolid()) {
				
				yPos -= yVel;
				yVel = 0;
				
				updateBoundingBox();
				
			}
			
		} else {
			
			setSpeedNormal();
			
			if(!isDead) {
			
				if(moveUp) {
					
					if(canJump()) yVel = -yVelLimit * 0.93f;
					
				}
			
			}
			
			yVel += yAccLimit * deltaTime * 0.01f;
			
			if(yVel > yVelLimit) yVel = yVelLimit;
			if(yVel < -yVelLimit) yVel = -yVelLimit;
			yPos += yVel;
			
			updateBoundingBox();

			if(isCollidingWithSolid()) {
				
				//yPos = findNearestMultipleOf(yPos, 16);
				yPos -= yVel;
				yVel = 0;
				
				updateBoundingBox();
				
			}
			
		}
			
		if(!isDead) {
		
			if(moveRight)
				xVel += xAccLimit * deltaTime * 0.01f;
			else if(moveLeft)
				xVel -= xAccLimit * deltaTime * 0.01f;
			
			if(moveLeft && moveRight)
				xVel = 0;
				
		}
			
			if(xVel > xVelLimit) xVel = xVelLimit;
			if(xVel < -xVelLimit) xVel = -xVelLimit;
			xPos += xVel;
			
			updateBoundingBox();
			
			if(isCollidingWithSolid()) {
				
				xPos -= xVel;
				xVel = 0;
				
				updateBoundingBox();
			
		}
		
	}
	
	public void tick(float deltaTime) {
		
		if(isCollidingWithLiquid()) {
			
			setSpeedLiquid();
			
			if(moveUp) {
				
				yVel -= yAccLimit * deltaTime * 0.01f;
				
				if(yVel > yVelLimit) yVel = yVelLimit;
				if(yVel < -yVelLimit) yVel = -yVelLimit;
				yPos += yVel;
				
			}
				
			
		} else {
			
			setSpeedNormal();
			
		}
		
		if(moveUp) {
		
			if(canJump()) yVel = -yVelLimit;
			
		}
		
		yVel += yAccLimit * deltaTime * 0.01f;
		
		if(yVel > yVelLimit) yVel = yVelLimit;
		if(yVel < -yVelLimit) yVel = -yVelLimit;
		yPos += yVel;
		
		updateBoundingBox();

		if(isCollidingWithSolid()) {
			
			yPos -= yVel;
			yVel = 0;
			
			updateBoundingBox();
			
		}
		
		if(moveRight)
			xVel += xAccLimit * deltaTime * 0.01f;
		else if(moveLeft)
			xVel -= xAccLimit * deltaTime * 0.01f;
		
		if(moveLeft && moveRight)
			xVel = 0;
		
		if(xVel > xVelLimit) xVel = xVelLimit;
		if(xVel < -xVelLimit) xVel = -xVelLimit;
		xPos += xVel;
		
		updateBoundingBox();
		
		if(isCollidingWithSolid()) {
			
			xPos -= xVel;
			xVel = 0;
			
			updateBoundingBox();
			
		}
		
	}
	
	private boolean isCollidingWithSolid() {
		
		Block[] nearbyBlocks = Game.lm.getCurrentLevel().getNearbyBlocks(xPos, yPos);
		
		for(int i = 0; i < nearbyBlocks.length; i++) {
			
			if(nearbyBlocks[i] != null && nearbyBlocks[i] instanceof BlockSolid) {
				
				BoundingBox currentBlockBB = nearbyBlocks[i].getBoundingBox();
				
				if(CollisionHelper.AABBColliding(this.bb, currentBlockBB)) {
					
					return true;
					
				}
				
			}
			
		}
		
		return false;
		
	}
	
	private boolean isCollidingWithLiquid() {
		
		Block[] nearbyBlocks = Game.lm.getCurrentLevel().getNearbyBlocks(xPos, yPos);
		
		for(int i = 0; i < nearbyBlocks.length; i++) {
			
			if(nearbyBlocks[i] != null && nearbyBlocks[i] instanceof BlockLiquid) {
				
				BoundingBox currentBlockBB = nearbyBlocks[i].getBoundingBox();
				
				if(CollisionHelper.AABBColliding(this.bb, currentBlockBB)) {
					
					return true;
					
				}
				
			}
			
		}
		
		return false;
		
	}
	
	private boolean isCollidingWithLadder() {
		
		Block[] nearbyBlocks = Game.lm.getCurrentLevel().getNearbyBlocks(xPos, yPos);
		
		for(int i = 0; i < nearbyBlocks.length; i++) {
			
			if(nearbyBlocks[i] != null && nearbyBlocks[i] instanceof BlockLadder) {
				
				BoundingBox currentBlockBB = nearbyBlocks[i].getBoundingBox();
				
				if(CollisionHelper.AABBColliding(this.bb, currentBlockBB)) {
					
					return true;
					
				}
				
			}
			
		}
		
		return false;
		
	}
	
	private boolean isCollidingWithDeath() {
		
		Block[] nearbyBlocks = Game.lm.getCurrentLevel().getNearbyBlocks(xPos, yPos);
		
		for(int i = 0; i < nearbyBlocks.length; i++) {
			
			if(nearbyBlocks[i] != null && nearbyBlocks[i].isDeadly()) {
				
				BoundingBox currentBlockBB = nearbyBlocks[i].getBoundingBox();
				
				if(CollisionHelper.AABBColliding(this.bb, currentBlockBB)) {
					
					return true;
					
				}
				
			}
			
		}
		
		return false;
		
	}
	
	private boolean isCollidingWithDoor() {
		
		Block[] nearbyBlocks = Game.lm.getCurrentLevel().getNearbyBlocks(xPos, yPos);
		
		for(int i = 0; i < nearbyBlocks.length; i++) {
			
			if(nearbyBlocks[i] != null) {
				
				if(nearbyBlocks[i] instanceof BlockDoor) {
					
					BoundingBox currentBlockBB = nearbyBlocks[i].getBoundingBox();
					BoundingBox doorCentre = new BoundingBox(currentBlockBB.getX() + 16,
							currentBlockBB.getY() + 16,
							currentBlockBB.getWidth(),
							currentBlockBB.getHeight());
					
					if(CollisionHelper.AABBColliding(this.bb, doorCentre)) return true;
					
				}
				
			}
			
		}
		
		return false;
		
	}
	
	private boolean isCollidingWithArrow() {
		
		for(int i = 0; i < Game.am.size(); i++) {
			
			if(CollisionHelper.AABBColliding(bb, Game.am.getArrow(i).getBB())) return true;
			
		}
		
		return false;
		
	}
	
	private void handleBlockBreaks() {
		
		Block[] nearbyBlocks = Game.lm.getCurrentLevel().getNearbyBlocks(xPos, yPos);
		
		for(int i = 0; i < nearbyBlocks.length; i++) {
			
			if(nearbyBlocks[i] != null && nearbyBlocks[i] instanceof BlockBreakable) {
				
				BoundingBox currentBlockBB = nearbyBlocks[i].getBoundingBox();
				BoundingBox extraBB = new BoundingBox(currentBlockBB.getX() - 1, currentBlockBB.getY() - 2, currentBlockBB.getWidth() + 2, currentBlockBB.getHeight() + 7);
				
				if(CollisionHelper.AABBColliding(this.bb, extraBB)) {
					
					//Logger.printDebug("COLLISION");
					((BlockBreakable) nearbyBlocks[i]).setIsBreaking(true);
					
				}
				
			}
			
			if(nearbyBlocks[i] != null && nearbyBlocks[i] instanceof BlockCoin) {
				
				BoundingBox currentBlockBB = nearbyBlocks[i].getBoundingBox();
				
				if(CollisionHelper.AABBColliding(this.bb, currentBlockBB)) {
					
					Game.lm.getCurrentLevel().setBlock(nearbyBlocks[i].getX() / 32, nearbyBlocks[i].getY() / 32, null);
					
				}
				
			}
			
		}
		
	}
	
	private boolean canJump() {
		
		Block blockBelow1 = Game.lm.getCurrentLevel().getBlock((int)(xPos + 31) / 32, (int)(yPos + 32) / 32);
		Block blockBelow2 = Game.lm.getCurrentLevel().getBlock((int)(xPos) / 32, (int)(yPos + 32) / 32);
		
		if(blockBelow1 instanceof BlockSolid &&
				((CollisionHelper.pointInsideBB((int)(xPos + 31), (int)(yPos + 33), blockBelow1.getBoundingBox())) || 
				 (CollisionHelper.pointInsideBB((int)(xPos + 15), (int)(yPos + 33), blockBelow1.getBoundingBox())))
		) return true;
		
		if(blockBelow2 instanceof BlockSolid &&
				((CollisionHelper.pointInsideBB((int)(xPos), (int)(yPos + 33), blockBelow2.getBoundingBox())) || 
				 (CollisionHelper.pointInsideBB((int)(xPos + 15), (int)(yPos + 33), blockBelow2.getBoundingBox())))
		) return true;
		
		return false;
		
	}
	
	@SuppressWarnings("unused")
	private int findNearestMultipleOf(float number, int multiple) {
		
		//System.out.println(number + ", " + multiple);
		int ratio = (int)(number / multiple); // Work out ratio, rounded down to nearest int
		int upper = (ratio + 1) * multiple;
		int lower = ratio * multiple;
		//System.out.println("Upper: " + upper + ", lower: " + lower);
		int upperDiff = (int)(number - upper);
		int lowerDiff = (int)(number - lower);
		
		if(Math.abs(upperDiff) < Math.abs(lowerDiff)) return upper;
		else return lower;
		
	}
	
	private void setSpeedLiquid() {
		
		xAccLimit = xAccMax / 1.8f;
		yAccLimit = yAccMax / 2.5f;
		xVelLimit = xVelMax / 1.8f;
		yVelLimit = yVelMax / 2.5f;
		
	}
	
	private void setSpeedLadder() {
		
		xVelLimit = xVelMax / 1.5f;
		yVelLimit = yVelMax / 1.5f;
		xAccLimit = xAccMax * 2.0f;
		yAccLimit = yAccMax * 2.0f;
		
	}
	
	private void setSpeedNormal() {
		
		xAccLimit = xAccMax;
		yAccLimit = yAccMax;
		xVelLimit = xVelMax;
		yVelLimit = yVelMax;
		
	}
	
	public float getXPos() { return xPos; }
	public float getYPos() { return yPos; }

	public void setDead(boolean b) { isDead = b; }
	
	public void setXVel(float xVel) { this.xVel = xVel; }
	public void setYVel(float yVel) { this.yVel = yVel; }
	public void setXPos(float xPos) { this.xPos = xPos; }
	public void setYPos(float yPos) { this.yPos = yPos; }
	
	public void setMoveRight(boolean b) { if(!isDead) moveRight = b; else moveRight = false; }
	public void setMoveLeft(boolean b) { if(!isDead) moveLeft = b; else moveLeft = false; }
	public void setMoveUp(boolean b) { if(!isDead) moveUp = b; else moveUp = false; }
	public void setMoveDown(boolean b) { if(!isDead) moveDown = b; else moveDown = false; }
	
}
