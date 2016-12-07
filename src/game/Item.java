package game;

public class Item {
	public enum ItemType {
		JAVA, PYTHON, CSHARP, HTML, CSS, JS
	}
	
	public static Item generate(ItemType itemType){
		
		return new Item();
	}
}
