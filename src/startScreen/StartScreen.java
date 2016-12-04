package startScreen;

import javafx.animation.FillTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class StartScreen extends StackPane {

	private static final StartScreen instace = new StartScreen();

	private BackgroundStartScreen backgroundStartScreen;
	private StartPane startPane;
	private SettingPane settingPane;
	private UpdatingPane updatingPane;
	private NewGamePane newGamePane;
	private LoadGamePane loadGamePane;

	public static StartScreen getInstace() {
		return instace;
	}

	public BackgroundStartScreen getBackgroundStartScreen() {
		return backgroundStartScreen;
	}

	public StartPane getStartPane() {
		return startPane;
	}

	public SettingPane getSettingPane() {
		return settingPane;
	}

	public UpdatingPane getUpdatingPane() {
		return updatingPane;
	}

	public NewGamePane getNewGamePane() {
		return newGamePane;
	}

	public LoadGamePane getLoadGamePane() {
		return loadGamePane;
	}

	public StartScreen() {
		super();
		// TODO Auto-generated constructor stub
		
		this.backgroundStartScreen = new BackgroundStartScreen();
		this.getChildren().add(backgroundStartScreen);

		this.startPane = new StartPane();
		this.getChildren().add(startPane);

		this.settingPane = new SettingPane();

		this.updatingPane = new UpdatingPane();

		this.newGamePane = new NewGamePane();

		this.loadGamePane = new LoadGamePane();
	}

	public void changePane(GridPane pane) {
		this.getChildren().clear();
		this.getChildren().add(backgroundStartScreen);
		this.getChildren().add(pane);

		TranslateTransition transition = new TranslateTransition();
		transition.setFromX(-1120);
		transition.setToX(0);
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(pane);
		transition.play();
	}
}
