package game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MapCellHolder {
	private List<List<MapCell>> mapCells;
	public static final MapCellHolder instance = new MapCellHolder(); 
	
	private MapCellHolder() {
		this.mapCells = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			this.mapCells.add(new ArrayList<MapCell>());
			for (int j = 0; j < 5; j++) {
				this.mapCells.get(i).add(new MapCell());
			}
		}
	}
	
	public List<List<MapCell>> getMapCells() {
		return this.mapCells;
	}
	
	public MapCell get(int x, int y) {
		return this.mapCells.get(y).get(x);
	}
}
