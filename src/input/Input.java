package input;

import java.util.List;

import javafx.scene.input.KeyCode;

public class Input {
	private List<KeyCode> keyPress;
	private List<KeyCode> keyDown;
	
	public boolean isKeyDown(KeyCode keycode) {
		return this.keyDown.contains(keycode);
	}
	
	public boolean isKeyPressed(KeyCode keycode) {
		return this.keyPress.contains(keycode);
	}
	
	public void receiveKeyPress(KeyCode keycode) {
		if(!this.keyDown.contains(keycode)) {
			this.keyDown.add(keycode);
			this.keyPress.add(keycode);
		}
	}
	
	public void receiveKeyRelease(KeyCode keycode) {
		if(this.keyDown.contains(keycode)) {
			this.keyDown.remove(keycode);
		}
	}
	
	public void inputUpdate() {
		this.keyPress.clear();
	}
}
