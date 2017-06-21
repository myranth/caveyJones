package client;

public class SceneManager {

	public enum Scene {
		
		MAINMENU, LEVEL, LEVELDEAD, LEVELCOMPLETE;
		
	}
	
	private Scene scene;
	
	public SceneManager() {
		
		this.scene = Scene.MAINMENU;
		
	}
	
	public Scene getScene() { return scene; }
	public void setScene(Scene scene) { this.scene = scene; }
	
}
