package client;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import client.SceneManager.Scene;
import level.Level;

public class Mouse implements MouseListener {

	private void resetCamCharacter() {
		
		Game.cavey.setXPos(Game.lm.getCurrentLevel().getSpawnX());
		Game.cavey.setYPos(Game.lm.getCurrentLevel().getSpawnY());
		Game.cam.setMaxHeight(Game.lm.getCurrentLevel().getHeight() * 32);
		Game.cam.setMaxWidth(Game.lm.getCurrentLevel().getWidth() * 32);
		
	}
	
	private void switchLevel(String levelName) {
		
		Game.sm.setScene(SceneManager.Scene.LEVEL);
		Game.lm.setCurrentLevel(levelName);
		Game.lm.getCurrentLevel().initBlockMap();
		Game.am.reset();
		resetCamCharacter();
		
	}
	
	public void mouseClicked(MouseEvent e) {

		int x = e.getX();
		int y = e.getY();
		
		if(Game.sm.getScene() == SceneManager.Scene.LEVEL) {
			
			if(Game.bm.getButton(0).isClicked(x, y)) {
				
				Game.sm.setScene(Scene.MAINMENU);
				Game.am.reset();
				
			}
			
			if(Game.bm.getButton(5).isClicked(x, y)) {
				
				Game.lm.getCurrentLevel().reset();
				Game.am.reset();
				
			}
			
		}
		
		if(Game.sm.getScene() == SceneManager.Scene.MAINMENU) {
			
			if(Game.bm.getButton(1).isClicked(x, y)) {
				
				switchLevel(Level.LEVEL_00_NAME);
				
			}
			
			if(Game.bm.getButton(2).isClicked(x, y)) {
				
				switchLevel(Level.LEVEL_01_NAME);
				
			}
			
			if(Game.bm.getButton(6).isClicked(x, y)) {
				
				switchLevel(Level.LEVEL_02_NAME);
				
			}
			
			if(Game.bm.getButton(7).isClicked(x, y)) {
				
				switchLevel(Level.LEVEL_03_NAME);
				
			}
			
			if(Game.bm.getButton(8).isClicked(x, y)) {
				
				switchLevel(Level.LEVEL_04_NAME);
				
			}
			
			if(Game.bm.getButton(9).isClicked(x, y)) {
				
				switchLevel(Level.LEVEL_05_NAME);
				
			}
			
			if(Game.bm.getButton(10).isClicked(x, y)) {
				
				switchLevel(Level.LEVEL_06_NAME);
				
			}
			
			if(Game.bm.getButton(11).isClicked(x, y)) {
				
				switchLevel(Level.LEVEL_07_NAME);
				
			}
			
			if(Game.bm.getButton(13).isClicked(x, y)) {
				
				switchLevel(Level.LEVEL_08_NAME);
				
			}
			
			if(Game.bm.getButton(14).isClicked(x, y)) {
				
				switchLevel(Level.LEVEL_09_NAME);
				
			}
			
			if(Game.bm.getButton(15).isClicked(x, y)) {
				
				switchLevel(Level.LEVEL_10_NAME);
				
			}
			
			if(Game.bm.getButton(16).isClicked(x, y)) {
				
				switchLevel(Level.LEVEL_11_NAME);
				
			}
			
			if(Game.bm.getButton(17).isClicked(x, y)) {
				
				switchLevel(Level.LEVEL_12_NAME);
				
			}
			
			if(Game.bm.getButton(18).isClicked(x, y)) {
				
				switchLevel(Level.LEVEL_13_NAME);
				
			}
			
			if(Game.bm.getButton(19).isClicked(x, y)) {
				
				switchLevel(Level.LEVEL_14_NAME);
				
			}
			
		}

		if(Game.sm.getScene() == SceneManager.Scene.LEVELDEAD) {
			
			if(Game.bm.getButton(3).isClicked(x, y)) {
				
				Game.cavey.setDead(false);
				Game.sm.setScene(SceneManager.Scene.LEVEL);
				Game.lm.getCurrentLevel().initBlockMap();
				Game.am.reset();
				resetCamCharacter();
				
			}
			
			if(Game.bm.getButton(4).isClicked(x, y)) {
				
				Game.cavey.setDead(false);
				Game.sm.setScene(SceneManager.Scene.MAINMENU);
				Game.cavey.setXPos(-1);
				Game.cavey.setYPos(-1);
				Game.am.reset();
				
			}
			
		}
		
		if(Game.sm.getScene() == SceneManager.Scene.LEVELCOMPLETE) {
			
			if(Game.bm.getButton(12).isClicked(x, y)) {
				
				switchLevel(Game.lm.getNextLevelName());
				resetCamCharacter();
				
			}
			
			if(Game.bm.getButton(4).isClicked(x, y)) {
				
				Game.cavey.setDead(false);
				Game.sm.setScene(SceneManager.Scene.MAINMENU);
				Game.cavey.setXPos(-1);
				Game.cavey.setYPos(-1);
				Game.am.reset();
				
			}
			
		}
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}