package launcher;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class TabbedSplashScreen extends StackPane{
	public TabbedSplashScreen() {
		super();
		this.setPrefHeight(500-120);
		TabControl tabControl = new TabControl();
		this.getChildren().add(tabControl);
	}
}

class TabControl extends TabPane {
	public TabControl() {
		super();
		Tab splashImageTab = new Tab();
		splashImageTab.setText("Splash Image");
		splashImageTab.setContent(new SplashImagePane());
		splashImageTab.setClosable(false);
		
		Tab changeLogTab = new Tab();
		changeLogTab.setText("Change Logs");
		changeLogTab.setContent(new ChangeLogPane());
		
		this.getTabs().addAll(splashImageTab,changeLogTab);
	}
}

class SplashImagePane extends StackPane {
	public SplashImagePane() {
		super();
		this.setStyle("-fx-background-color: gray;");
		Label placeHolder = new Label("Splash Image");
		this.getChildren().add(placeHolder);
	}
}

class ChangeLogPane extends StackPane {
	protected TextArea textArea;
	public ChangeLogPane() {
		super();
		this.setPrefHeight(500-120);
		
		this.textArea = new TextArea();
		this.textArea.setEditable(false);
		this.textArea.setPrefSize(this.getPrefWidth(), this.getPrefHeight());
		this.textArea.setWrapText(true);
		String placeHolderText = "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nihil suscipit, molestias cumque quidem explicabo quibusdam. Eius repudiandae explicabo molestias voluptatem praesentium. Laboriosam, recusandae nemo quod cumque dolor ducimus suscipit nulla.";
		this.textArea.setText(placeHolderText);
		
		this.setMinHeight(this.getPrefHeight());
		this.getChildren().add(textArea);
	}
}