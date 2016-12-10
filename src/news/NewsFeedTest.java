package news;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import market.Market;

public class NewsFeedTest extends Application {

	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		super.stop();
		Market.finalizeMarket();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		
		Market.InitializeMarket();
		
		StackPane root = new StackPane();
		NewsPane newsPane = NewsPane.instance;
		
		newsPane.getNewsFeed().addNews(new News("python","Why you no use me anymore? :sadface:"));
		newsPane.getNewsFeed().addNews(new News("java","NullPointerException gonna rekt us so hard"));
		newsPane.getNewsFeed().addNews(new News("peawyoyoyin","this is the news feed"));
		newsPane.getNewsFeed().addNews(new News("roy lek", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Platform.runLater(new Runnable() {
					public void run() {
						newsPane.getNewsFeed().addNews(new News("Hello world","yea"));
					}
				});
			}
		}).start();
		
		root.getChildren().add(newsPane);
		
		Scene scene = new Scene(root,250,500);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
