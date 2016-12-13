package input;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class Input {
	private static List<KeyCode> keyPress;
	private static List<KeyCode> keyDown;
	
	public static void Initialize(Scene scene) {
		Input.keyPress = new ArrayList<>();
		Input.keyDown = new ArrayList<>();
		scene.setOnKeyPressed(event -> {
			Input.receiveKeyPress(event.getCode());
		});
		
		scene.setOnKeyReleased(event -> {
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
