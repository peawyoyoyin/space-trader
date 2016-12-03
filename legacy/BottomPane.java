package launcher;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class BottomPane extends BorderPane{
	StartButtonPane startButtonPane;
	ConfigButtonPane configButtonPane;
	public BottomPane() {
		super();
		this.setPrefSize(800, 120);
		this.startButtonPane = new StartButtonPane();
		this.setCenter(startButtonPane);
		this.configButtonPane = new ConfigButtonPane();
		this.setRight(configButtonPane);
	}
}

class StartButtonPane extends StackPane {
	protected Button startButton;
	public StartButtonPane() {
		super();
		this.startButton = new Button("Start Game");
		this.startButton.setPrefSize(400, 90);
		this.getChildren().add(startButton);
	}
}

class ConfigButtonPane extends VBox {
	protected Button configButton;
	protected Button updateButton;
	public ConfigButtonPane() {
		super();
		this.setPrefSize(200,120);
		this.setAlignment(Pos.CENTER);
		this.setSpacing(10);
		this.configButton = new Button("Config");
		this.updateButton = new Button("Update");
		this.getChildren().addAll(configButton, updateButton);
	}
}