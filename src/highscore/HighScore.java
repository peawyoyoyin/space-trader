package highscore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import constants.ConfigConstant;

public class HighScore {
	private static final int MAX_ENTRY = 5;
	
	private static String highScores;
	
	public static String getHighScores() throws HighScoreParsingException {
		if(highScores == null) {
			loadHighScore();
		}
		return highScores;
	}
	
	public static boolean isInHighScore(int score) {
		List<HighScoreRecord> highScoreRecords = HighScoreRecord.getAllHighScoreRecords();
		if(highScoreRecords.isEmpty()) {
			return true;
		}
		if(highScoreRecords.size() < MAX_ENTRY) {
			return true;
		}
		for (HighScoreRecord highScoreRecord : highScoreRecords) {
			if(score > highScoreRecord.getScore()) {
				return true;
			}
		}
		return false;
	}
	
	public static void addHighScoreEntry(String name, int score) {
		new HighScoreRecord(name, score);
		sortHighScoreRecords();
		saveHighScore();
	}
	
	public static void sortHighScoreRecords() {
		System.out.println("sortHighScoreRecords");
		List<HighScoreRecord> highScoreRecords = HighScoreRecord.getAllHighScoreRecords();	
		Comparator<HighScoreRecord> c = new Comparator<HighScoreRecord>() {
			@Override
			public int compare(HighScoreRecord a, HighScoreRecord b) {
				// TODO Auto-generated method stub
				if(a.getScore() > b.getScore()) {
					return -1;
				} else {
					return 1;
				}
			}
		};
		highScoreRecords.sort(c);
		HighScoreRecord.setAllHighScoreRecords(highScoreRecords.subList(0, highScoreRecords.size() < MAX_ENTRY ? highScoreRecords.size() : MAX_ENTRY));
		System.out.println(highScoreRecords);
	}
	
	public static void saveHighScore() {
		List<HighScoreRecord> highScoreRecords = HighScoreRecord.getAllHighScoreRecords();
		System.out.println(highScoreRecords);
		String out = "";
		sortHighScoreRecords();
		File highScoreFile = new File(ConfigConstant.HIGHSCORE_FILE);
		try {
			out += "{\r\n";
			for (HighScoreRecord highScoreRecord : highScoreRecords) {
				out += highScoreRecord.toString() + "\r\n";
			}
			out += "}\n";
			BufferedWriter writer = new BufferedWriter(new FileWriter(highScoreFile));
			writer.write(out);
			writer.close();
		} catch (IOException e) {
			System.err.println("saveHighScore failed");
		}

	}
	
	public static void loadHighScore() throws HighScoreParsingException {
		HighScoreRecord.getAllHighScoreRecords().clear();
		File highScoreFile = new File(ConfigConstant.HIGHSCORE_FILE);
		try {
			BufferedReader highScore = new BufferedReader(new FileReader(highScoreFile));
			int c;
			while((c = highScore.read()) != '{') {
				if(c == -1) {
					throw new HighScoreParsingException("{");
				}
			}
			String read = "";
			while((c = highScore.read()) != '}') {
				if(c == -1) {
					throw new HighScoreParsingException("}");
				}
				read += (char) c;
			}
			List<String> tokens = Arrays.asList(read.split("\n")).stream().filter(t -> t.trim().length() > 0).collect(Collectors.toList());
			tokens.replaceAll(t -> t.trim());
			for (String string : tokens) {
				System.out.println(string);
				new HighScoreRecord(string);
			}
			sortHighScoreRecords();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			new HighScoreRecord("AAA", 500);
			new HighScoreRecord("BBB", 500);
			new HighScoreRecord("CCC", 500);
			new HighScoreRecord("DDD", 500);
			new HighScoreRecord("EEE", 500);
			saveHighScore();
		} catch (IOException e) {
			throw new HighScoreParsingException("io");
		}
	}
}