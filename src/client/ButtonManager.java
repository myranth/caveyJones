package client;

import graphics.Button;

import java.util.ArrayList;

public class ButtonManager {

	// I obviously got very lazy with my managers here.
	// 0 = Main menu button in top right while on level
	// 1 = Button for level 1
	// 2 = Button for level 2
	// 3 = Respawn button when dead
	// 4 = Main menu button when dead
	// 5 = Respawn button in level
	// 6 = Button for level 3
	// 7 = Button for level 4
	// 8 = Button for level 5
	// 9 = Button for level 6
	// 10 = Button for level 7
	// 11 = Button for level 8
	// 12 = Next level button
	// 13 = Button for level 9
	private ArrayList<Button> buttons;
	
	public ButtonManager() {
		
		buttons = new ArrayList<Button>();
		
	}
	
	public void addButton(Button b) { buttons.add(b); }
	
	public Button getButton(int i) { return buttons.get(i); }
	
}