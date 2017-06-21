package graphics;


public interface IGraphicsManager {

	public boolean init();
	
	public boolean addTexture(String fileName, String textureName);
	public boolean addSprite(Texture texture, int x, int y, int width, int height, String spriteName);
	
	public Texture getTexture(String textureName);
	public Sprite getSprite(String spriteName);
	
}