package game.logic;

import java.util.ArrayList;
import java.util.List;

public class MapCellHolder {
	private List<List<MapCell>> mapCells;
	public static MapCellHolder instance = new MapCellHolder(); 
	
	public MapCellHolder() {
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
