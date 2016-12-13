package news;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class News {
	private String author;
	private String content;
	
	public static List<News> randomNews = new ArrayList<>();
	
	static {
		randomNews.add(new News("peawyoyoyin","progmeth final is too hard for me."));
		randomNews.add(new News("roy lek","You call this an exam?"));
		randomNews.add(new News("naisk133", "Hope I get Rank 1 on discrete again."));
		randomNews.add(new News("enginex", "enginex co-working space is now open! 6th floor, 100th year building"));
		randomNews.add(new News("moomoo", "bought a new board game"));
		randomNews.add(new News("thinc", "app development is kinda hard"));
		randomNews.add(new News("kp666-pun","I'm the best programmer in SE."));
		randomNews.add(new News("dev team","this game is full of bugs."));
		randomNews.add(new News("levitate","progmeth's project is too hard."));
		randomNews.add(new News("Drump","make galaxy great again."));
		randomNews.add(new News("GA games","Space Football 3012 is out now on horizon!"));
		randomNews.add(new News("dev","spaghetti code ftw"));
		randomNews.add(new News("Unicode","%kfdo$9dak#kd!"));
		randomNews.add(new News("DOPA2","DOPA 9.00 is now live!"));
		randomNews.add(new News("Imperial","I found my son and he kissed my daughter! #blessed"));
		randomNews.add(new News("Zulu","why are we not warping?"));
		randomNews.add(new News("roy","will I ever get a girlfriend?"));
		randomNews.add(new News("regLucha","regLucha is down due to heavy load."));
	}
	
	public static News getRandomNews() {
		return randomNews.get(new Random().nextInt(randomNews.size()));
	}
	
	public News(String author) {
		this.author = author;
		this.content = "blank news";
	}
	
	public News(String author, String content) {
		this.author = author;
		this.content = content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public String getContent() {
		return this.content;
	}
}
