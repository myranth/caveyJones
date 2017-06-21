package level;


public interface ILevelManager {

	public boolean init();
	
	public void addLevel(String fileName, String levelName);
	public boolean loadLevel(String levelName);
	public boolean isLevelLoaded(String levelName);
	public boolean isLevelAdded(String levelName);
	
	public Level getLevel(String levelName);
	
}
