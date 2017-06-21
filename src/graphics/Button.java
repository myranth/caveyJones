package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Button {

	private int xPos;
	private int yPos;
	private int width;
	private int height;
	private String text;
	private Font font;
	private int xOffset;
	
	private int xText;
	private int yText;
	
	public Button(int xPos, int yPos, int width, int height, String text, Font font, int xOffset) {
		
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		this.text = text;
		this.font = font;
		this.xOffset = xOffset;
		
		this.xText = xPos + (width / 2) - (text.length() * 4);
		this.yText = yPos + (height / 2) + (this.font.getSize() / 2) - 4;
		
	}
	
	public void render(Graphics g) {
		
		g.setColor(new Color(200, 200, 200, 180));
		g.fillRect(xPos, yPos, width, height);
		g.setColor(new Color(0, 0, 0, 255));
		g.drawRect(xPos, yPos, width, height);
		g.setFont(font);
		g.drawString(text, xText + xOffset, yText);
		
	}
	
	public boolean isClicked(int x, int y) {
		
		return !(x < xPos || y < yPos || x > (xPos + width) || y > (yPos + height));
		
	}
	
}
