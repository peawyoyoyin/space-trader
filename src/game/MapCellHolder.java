package game;

import java.util.ArrayList;
import java.util.List;

public class MapCellHolder {
	private List<MapCell> mapCells;
	public static final MapCellHolder instance = new MapCellHolder(); 
	
	private MapCellHolder() {
		this.mapCells = new ArrayList<MapCell>();
	}
	
	public List<MapCell> getMapCells() {
		return this.mapCells;
	}
}
