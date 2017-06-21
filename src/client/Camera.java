package client;

public class Camera {

	private float xPos;
	private float yPos;
	private int width;
	private int height;
	private int widthMax;
	private int heightMax;
	
	public Camera(int xPos, int yPos, int width, int height, int widthMax, int heightMax) {
		
		Logger.printDebug("Creating camera at (" + xPos + ", " + yPos + ")");
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		this.widthMax = widthMax;
		this.heightMax = heightMax;
		
	}
	
	public void tick(float deltaTime) {
		
		xPos = Game.cavey.getXPos() - 400.0f;
		yPos = Game.cavey.getYPos() - 320.0f;
		
		if(xPos < 0) xPos = 0;
		if(yPos < 0) yPos = 0;
		
		if(xPos + width > widthMax) xPos = widthMax - width;
		if(yPos + height > heightMax) yPos = heightMax - height;
		
	}
	
	public void setMaxWidth(int w) { widthMax = w; }
	public void setMaxHeight(int h) { heightMax = h; }
	
	public float getX() { return xPos; }
	public float getY() { return yPos; }
	
}