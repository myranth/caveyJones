package entity;

import client.Game;
import client.Logger;
import maths.BoundingBox;

public class Hero extends EntityHero {
	
	public static float xAcc = 1.6f;
	public static float yAcc = 0.9f;
	public static float xVelMax = 3.4f;
	public static float yVelMax = 5.81f;
	
	public Hero(float xPos, float yPos) {
		
		super(xPos, yPos, xAcc, yAcc, xVelMax, yVelMax, new BoundingBox(xPos, yPos, 32, 32));

		Logger.printDebug("Cavey!");
		sprite = Game.gm.getSprite("caveySprite");
		
	}
	
}