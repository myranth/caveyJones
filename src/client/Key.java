package client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Key implements KeyListener {

	private void resetCamCharacter() {
		
		Game.cavey.setXPos(Game.lm.getCurrentLevel().getSpawnX());
		Game.cavey.setYPos(Game.lm.getCurrentLevel().getSpawnY());
		Game.cam.setMaxHeight(Game.lm.getCurrentLevel().getHeight() * 32);
		Game.cam.setMaxWidth(Game.lm.getCurrentLevel().getWidth() * 32);
		
	}
	
	public void keyPressed(KeyEvent e) {

		if(Game.sm.getScene() == SceneManager.Scene.LEVEL || Game.sm.getScene() == SceneManager.Scene.LEVELDEAD) {
			
			if(e.getKeyCode() == KeyEvent.VK_R) {
			
				Game.cavey.setDead(false);
				Game.sm.setScene(SceneManager.Scene.LEVEL);
				Game.lm.getCurrentLevel().initBlockMap();
				Game.am.reset();
				resetCamCharacter();
			
			}
			
		}
		
		if(Game.sm.getScene() == SceneManager.Scene.LEVEL) {
			
			switch(e.getKeyCode()) {
			
			case KeyEvent.VK_RIGHT:
				Game.cavey.setMoveRight(true);
				break;
			case KeyEvent.VK_LEFT:
				Game.cavey.setMoveLeft(true);;
				break;
			case KeyEvent.VK_UP:
				Game.cavey.setMoveUp(true);
				break;
			case KeyEvent.VK_DOWN:
				Game.cavey.setMoveDown(true);
				break;
			
			}
			
		}
		
	}

	public void keyReleased(KeyEvent e) {

		switch(e.getKeyCode()) {
			
		case KeyEvent.VK_LEFT:
			Game.cavey.setMoveLeft(false);
			Game.cavey.setXVel(0);
			break;
		case KeyEvent.VK_RIGHT:
			Game.cavey.setMoveRight(false);
			Game.cavey.setXVel(0);
			break;
		case KeyEvent.VK_UP:
			Game.cavey.setMoveUp(false);
			break;
		case KeyEvent.VK_DOWN:
			Game.cavey.setMoveDown(false);
		
		}
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
