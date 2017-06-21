package maths;

public class BoundingBox {

	private float xPos;
	private float yPos;
	private float width;
	private float height;

	public BoundingBox(float xPos, float yPos, float width, float height) {
		
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		
	}
	
	public float getX() { return xPos; }
	public float getY() { return yPos; }
	public float getWidth() { return width; }
	public float getHeight() { return height; }
	
	public void setX(float x) { this.xPos = x; }
	public void setY(float y) { this.yPos = y; }
	public void setWidth(float width) { this.width = width; }
	public void setHeight(float height) { this.height = height; }
	
}