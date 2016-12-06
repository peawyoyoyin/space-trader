package input;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;

public class Input {
	private static List<KeyCode> keyPress;
	private static List<KeyCode> keyDown;
	
	public static void Initialize(Node node) {
		node.setOnKeyPressed(event -> {
			Input.receiveKeyPress(event.getCode());
		});
		
		node.setOnKeyReleased(event -> {
			Input.receiveKeyRelease(event.getCode());
		});
	}
	
	public static boolean isKeyDown(KeyCode keycode) {
		return keyDown.contains(keycode);
	}
	
	public static boolean isKeyPressed(KeyCode keycode) {
		return keyPress.contains(keycode);
	}
	
	public static void receiveKeyPress(KeyCode keycode) {
		if(!keyDown.contains(keycode)) {
			keyDown.add(keycode);
			keyPress.add(keycode);
		}
	}
	
	public static void receiveKeyRelease(KeyCode keycode) {
		if(keyDown.contains(keycode)) {
			keyDown.remove(keycode);
		}
	}
	
	public static void inputUpdate() {
		keyPress.clear();
	}
}
