package entity;

import java.awt.Graphics;
import java.util.ArrayList;

import client.Logger;

public class ArrowManager {

	private ArrayList<EntityArrow> arrows;
	
	public ArrowManager() {
		
		arrows = new ArrayList<EntityArrow>();
		
	}
	
	public void add(float xPos, float yPos, int direction) {
		
		arrows.add(new EntityArrow(xPos, yPos, direction));
		Logger.printLog("Added a new arrow @ (" + xPos + "," + yPos + ")");
		
	}
	
	public void tick(float dt) {
		
		for(int i = 0; i < arrows.size(); i++) {
			
			arrows.get(i).tick(dt);
			
			if(arrows.get(i).isBroken()) arrows.remove(i);
			
		}
		
	}
	
	public void render(Graphics g) {
		
		for(int i = 0; i < arrows.size(); i++) {
			
			arrows.get(i).render(g);
			
		}
		
	}
	
	public void reset() {
		
		arrows.clear();
		
	}
	
	public int size() { return arrows.size(); }
	public EntityArrow getArrow(int i) { return arrows.get(i); }
	
}