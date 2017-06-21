package maths;

public class CollisionHelper {

	public static boolean AABBColliding(BoundingBox b1, BoundingBox b2) {

		if(b1.getX() + b1.getWidth() - 1 < b2.getX() ||
		   b1.getY() + b1.getHeight() - 1 < b2.getY() ||
		   b1.getX() > b2.getX() + b2.getWidth() ||
		   b1.getY() > b2.getY() + b2.getHeight()) {
			
			return false;
			
		}
		
		return true;
		
	}
	
	public static boolean pointInsideBB(int x, int y, BoundingBox b) {
		
		if(x < b.getX() || x > (b.getX() + b.getWidth()) ||
		   y < b.getY() || y > (b.getY() + b.getHeight())) return false;
		
		return true;
		
	}
	
}
