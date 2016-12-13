package highscore;

import java.util.ArrayList;
import java.util.List;

public class HighScoreRecord {
	public static List<HighScoreRecord> highScoreRecords = new ArrayList<>();

	private String name;
	private int score;
	
	public static List<HighScoreRecord> getAllHighScoreRecords() {
		return highScoreRecords;
	}
	
	public static void setAllHighScoreRecords(List<HighScoreRecord> highScoreRecords) {
		HighScoreRecord.highScoreRecords = highScoreRecords;
	}
	
	public HighScoreRecord(String name, int score) {
		this.name = name;
		this.score = score;
		highScoreRecords.add(this);
	}
	
	public HighScoreRecord(String entry) throws HighScoreParsingException {
		String nameString = entry.substring(0, entry.indexOf(':')).trim();
		String scoreString;
		try {
		scoreString = entry.substring(entry.indexOf(':')+1).trim();
		} catch(IndexOutOfBoundsException e) {
			System.err.println(entry);
			throw new HighScoreParsingException("Parse line failed : Score is empty");
		}
		
		if(nameString.isEmpty()) {
			nameString = "< blank name >";
		}
		int score;
		try {
		score = Integer.parseInt(scoreString);
		} catch (NumberFormatException e) {
			System.err.println(scoreString);
			throw new HighScoreParsingException("Parse score failed : score is not number");
		}
		this.name = nameString;
		this.score = score;
		
		highScoreRecords.add(this);
	}
	
	public int getScore() {
		return this.score;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name + " : " + this.score;
	}

	public String getName() {
		return name;
	}
}
