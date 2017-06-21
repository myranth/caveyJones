package graphics;

import java.awt.Image;

public class Sprite {

	private Image sprite;
	
	public Sprite(Texture texture, int x, int y, int width, int height) {
		
		sprite = texture.getImage().getSubimage(x, y, width, height);
		
	}
	
	public Image getSprite() { return sprite; }
	
}
