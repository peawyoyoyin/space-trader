package news;

public class News {
	private String author;
	private String content;
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
