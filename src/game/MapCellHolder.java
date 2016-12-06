package game;

import java.util.ArrayList;
import java.util.List;

public class MapCellHolder {
	private List<MapCell> mapCells;
	public static final MapCellHolder instance = new MapCellHolder(); 
	
	private MapCellHolder() {
		this.mapCells = new ArrayList<MapCell>(25);
	}
	
	public List<MapCell> getMapCells() {
		return this.mapCells;
	}
	
	public MapCell get(int x, int y) {
		return this.mapCells.get(y*5+x);
	}
}
