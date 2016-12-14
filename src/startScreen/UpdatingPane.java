package startScreen;

import constants.ConfigConstant;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class UpdatingPane extends GridPane {

	private VBox container = new VBox();
	private String textlog = "";

	public UpdatingPane() {
		super();
		// TODO Auto-generated constructor stub
		Text textName = new Text("Update");
		textName.setFont(ConfigConstant.Resource.START_SCREEN_FONT);
		textName.setFill(Color.WHITE);
		textName.setStroke(Color.BLACK);
		textName.setStrokeWidth(2);
		this.add(textName, 0, 0);
		GridPane.setMargin(textName, new Insets(20, 0, 20, 80));

		Text textBack = new Text("Back");
		textBack.setFont(ConfigConstant.Resource.START_SCREEN_ITEM_FONT);
		textBack.setFill(Color.WHITE);
		textBack.setStroke(Color.BLACK);
		textBack.setStrokeWidth(2);
		textBack.setEffect(new DropShadow());
		this.add(textBack, 0, 2);
		GridPane.setMargin(textBack, new Insets(20, 0, 80, 80));

		textBack.setOnMouseClicked(e -> {
			StartScreen.getInstance().changePane(StartScreen.getInstance().getStartPane());
			container.getChildren().clear();
			textlog = "";
		});
		textBack.setOnMouseEntered(e -> {
			textBack.setEffect(new Glow(0.5));
		});
		textBack.setOnMouseExited(e -> {
			textBack.setEffect(new DropShadow());
		});

		container.setPrefWidth(1120);
		container.setAlignment(Pos.CENTER);
		container.setSpacing(10);
		this.add(container, 0, 1);
		GridPane.setVgrow(container, Priority.ALWAYS);
	}

	public void startUpdate() {
		Text textStatus = new Text("Connecting to server");
		textStatus.setFont(ConfigConstant.Resource.START_SCREEN_SMALL_FONT);
		textStatus.setFill(Color.WHITE);
		textStatus.setStroke(Color.BLACK);
		textStatus.setStrokeWidth(2);
		container.getChildren().add(textStatus);

		AnimationTimer animater = new AnimationTimer() {
			int counter = 0;
			int countDot = 1;
			String status = "Connecting to server";

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				counter++;
				if (counter > 30) {
					counter = 0;
					countDot++;
					if (countDot > 3) {
						countDot = 1;
					}
				}
				String textDot = "";
				for (int i = 0; i < countDot; i++) {
					textDot = textDot + ".";
				}
				textStatus.setText(status + textDot);
			}
		};
		animater.start();
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					URL server = new URL(constants.ConfigConstant.serverURL);
					URLConnection connection = server.openConnection();
					BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					animater.stop();
					String version = in.readLine();
					String inputLine;
					textlog = in.readLine();
					while ((inputLine = in.readLine()) != null) {
						textlog = textlog + "\r\n" + inputLine;
					}
					if (version.equals(constants.ConfigConstant.version)) {
						Platform.runLater(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								textStatus.setText(ConfigConstant.UPTODATE_TEXT);
							}
						});
					} else {
						TextArea changelog = new TextArea();
						
						changelog.setMaxWidth(800);
						changelog.setPrefHeight(450);
						changelog.setEditable(false);
						Platform.runLater(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								textStatus.setText("New version " + version);
								container.getChildren().add(changelog);
								changelog.setText(textlog);
								changelog.setStyle(
										"-fx-control-inner-background:#000000;-fx-font-family: Consolas;-fx-highlight-fill: #00ff00;-fx-highlight-text-fill: #000000;-fx-text-fill: #00ff00;-fx-background-color: #000000;");
								
							}
						});
					}

					in.close();
				} catch (Exception e) {
					// TODO: handle exception
					animater.stop();
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							textStatus.setText(ConfigConstant.SERVER_NOT_FOUND_TEXT);
						}
					});

				}
			}
		});
		thread.start();
	}
}
