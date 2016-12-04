package startScreen;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class UpdatingPane extends GridPane {

	private VBox container = new VBox();
	private String textlog = "";

	public UpdatingPane() {
		super();
		// TODO Auto-generated constructor stub
		Text textName = new Text("Update");
		textName.setFont(Font.font(72));
		this.add(textName, 0, 0);
		GridPane.setMargin(textName, new Insets(20, 0, 0, 80));

		Text textBack = new Text("Back");
		textBack.setFont(Font.font(56));
		this.add(textBack, 0, 2);
		GridPane.setMargin(textBack, new Insets(20, 0, 80, 80));

		textBack.setOnMouseClicked(e -> {
			StartScreen.getInstace().changePane(StartScreen.getInstace().getStartPane());
			container.getChildren().clear();
			textlog = "";
		});

		container.setPrefWidth(1120);
		container.setAlignment(Pos.TOP_CENTER);
		container.setSpacing(10);
		this.add(container, 0, 1);
		GridPane.setVgrow(container, Priority.ALWAYS);
	}

	public void startUpdate() {
		Text textStatus = new Text("Connecting to server");
		textStatus.setFont(Font.font(48));
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
								textStatus.setText("Up to date already");
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
							textStatus.setText("Server not found");
						}
					});

				}
			}
		});
		thread.start();
	}
}
